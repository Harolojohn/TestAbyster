package com.Api_Parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api_Parking.Model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer>{

}
