package com.Api_Parking.Controller;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Api_Parking.Model.ParkingModel;
import com.Api_Parking.Model.RequestToPayObject;
import com.Api_Parking.Model.RequestToPayObjectSuccess;
import com.Api_Parking.Model.Ticket;
import com.Api_Parking.Model.TicketModelClient;
import com.Api_Parking.Service.MtnService;
import com.Api_Parking.Service.ParkingService;
import com.Api_Parking.Service.TicketService;





@RestController
@CrossOrigin(origins = "*")
public class ParkingController {
	
	@Autowired
	private ParkingService parkingService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private MtnService mtnService;
	
	
	@PostMapping("/PostParking")
	public void PostParking (@RequestBody ParkingModel parking,HttpServletRequest request, HttpServletResponse response){
		
		ParkingModel parkingToSave = new ParkingModel();
		
		parkingToSave.setNom(parking.getNom());
		parkingToSave.setEmplacement(parking.getEmplacement());
		
		parkingService.save(parkingToSave);
			
		
		response.setStatus(10000);	
	}
	
	@GetMapping("/GetParking")
	public List<ParkingModel> GetParking (HttpServletRequest request, HttpServletResponse response){

		System.out.println("GetParking");
		
		response.setStatus(10000);	
		
		return parkingService.Getparking();
		
	}
	
	
	@PostMapping("/PostTicket")
	public void PostTicket (@RequestBody TicketModelClient ticketClient,HttpServletRequest request, HttpServletResponse response){
		
		Ticket ticket = new Ticket();
		
		ticket.setNom(ticketClient.getNom());
		ticket.setDuree(ticketClient.getDuree());
		
		
		Optional<ParkingModel> parkingfotTicket;
		
		parkingfotTicket=parkingService.getparkingbyid(ticketClient.getId_parking());
		
		
		ticket.setParking(parkingfotTicket.get());
		
		ticketService.save(ticket);
		
		response.setStatus(10000);	
		
	}

	@GetMapping("/GetTicket")
	public List<Ticket> GetTicket (HttpServletRequest request, HttpServletResponse response){

		System.out.println("GetTicket");
		
		response.setStatus(10000);	
		
		return ticketService.GetTicket();
		
	}
	
	@PostMapping("/AttribuerTicket")
	public void AttribuerTicket (@RequestBody int id_ticket,HttpServletRequest request, HttpServletResponse response){
		
		
		Optional<Ticket> ticket;
		
		ticket=ticketService.findTicketbyid(id_ticket);
		
		ticket.get().setAttribue(true);
		
		ticketService.save(ticket.get());
		
		response.setStatus(10000);	
		
	}
	
	
	@PostMapping("/RequesttopayMTN")
	public RequestToPayObjectSuccess RequesttopayMTN (@RequestBody  RequestToPayObject requestToPayObject,HttpServletRequest request, HttpServletResponse response){
		
		
		 //Execution du request to pay	 
		 
		 // Genration du referenceId;
			
		 String referenceId=mtnService.GenrateReferenceId();
		 
		 
		// API USER
		 
		 String SubscriptionKey="e6c8c7cbda814847b4cbc5cd0ade12f7";
			
		mtnService.Api_user_post(referenceId, SubscriptionKey);
		 
		 
		//API KEY
		 
		//Gestion des erreurs
		 
		String Apikey = "";
		
		Apikey= mtnService.Api_key_post(referenceId,SubscriptionKey);
		
		System.out.println("\n\nAPI KEY: "+Apikey);
		
		//TOKEN
//		
		 String autorisation= referenceId+":"+Apikey;
			 
		 String autorisation64 = Base64.getEncoder().encodeToString(autorisation.getBytes());
			
		 String token="";
		
		 System.out.println("\nAutorisation encode base 64: "+autorisation64);
		 
		token=mtnService.post_token(autorisation64, SubscriptionKey);		
			
		System.out.println("\nTokenn: "+token);
		
		//Request pay  	
		
		 String autorisation_token= "Bearer "+token;
		 
		 // montant et numero payeur
		 
		 Integer montant_a_debiter=requestToPayObject.getMontant();
		 String numero_payeur=requestToPayObject.getNumero_debiteur();
		 String numero_destinataire="677994567";
		 
		
		 
		 mtnService.requestToPay(autorisation_token,referenceId,SubscriptionKey,montant_a_debiter,numero_payeur,numero_destinataire);
		 
		
		 
		//Request pay   STATUS TRANSACTION
		 
		 RequestToPayObjectSuccess info_transactions;
		 
		 info_transactions= mtnService.requestToPayStatus(autorisation_token, referenceId,SubscriptionKey);
		 
		 
		 return info_transactions;
	}
}
