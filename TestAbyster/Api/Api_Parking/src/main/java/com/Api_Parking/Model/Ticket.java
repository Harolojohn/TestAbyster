package com.Api_Parking.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "duree")
	private int duree;
	
	@Column(name = "attribue")
	private boolean attribue=false;
	
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, })
	@JoinColumn(name = "id_parking", referencedColumnName = "id")
	private ParkingModel parking;
	
	
	
	
	

	public boolean isAttribue() {
		return attribue;
	}

	public void setAttribue(boolean attribue) {
		this.attribue = attribue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public ParkingModel getParking() {
		return parking;
	}

	public void setParking(ParkingModel parking) {
		this.parking = parking;
	}
	
	
	
	

}
