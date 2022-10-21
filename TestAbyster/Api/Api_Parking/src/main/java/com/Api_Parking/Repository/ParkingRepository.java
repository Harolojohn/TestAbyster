package com.Api_Parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Api_Parking.Model.ParkingModel;




@Repository
public interface ParkingRepository extends JpaRepository<ParkingModel,Integer> {

}
