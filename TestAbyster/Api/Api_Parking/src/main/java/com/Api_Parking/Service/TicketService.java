package com.Api_Parking.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api_Parking.Model.ParkingModel;
import com.Api_Parking.Model.Ticket;
import com.Api_Parking.Repository.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	private	TicketRepository ticketRepository;
	
	@Transactional
	public ParkingModel save(Ticket ticket)  {
		
		ticketRepository.save(ticket);
		
		System.out.println("termine");
		
		return null;	
		
	}
	
	@Transactional
	public List<Ticket> GetTicket()  {
		
		return ticketRepository.findAll();		
		
	}
	
	@Transactional
	public Optional<Ticket> findTicketbyid(int id)  {
		
		return ticketRepository.findById(id);		
		
	}

}
