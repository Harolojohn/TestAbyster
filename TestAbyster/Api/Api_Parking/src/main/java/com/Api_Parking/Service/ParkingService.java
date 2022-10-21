package com.Api_Parking.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Api_Parking.Model.ParkingModel;
import com.Api_Parking.Repository.ParkingRepository;



@Service
public class ParkingService {
	
	@Autowired
	private	ParkingRepository parkingRepository;
	
	@Transactional
	public ParkingModel save(ParkingModel parking)  {
		
		System.out.println(parking.getNom());
		System.out.println(parking.getEmplacement());
		
		parkingRepository.save(parking);
		
		System.out.println("termine");
		
		return null;	
		
	}
	
	@Transactional
	public List<ParkingModel> Getparking()  {
		
		return parkingRepository.findAll();		
		
	}

	@Transactional
	public Optional<ParkingModel> getparkingbyid(int id)  {
		
		return parkingRepository.findById(id);		
		
	}

}
