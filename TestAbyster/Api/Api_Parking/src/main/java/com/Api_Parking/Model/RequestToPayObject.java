package com.Api_Parking.Model;

public class RequestToPayObject {
	
	
	private String numero_debiteur;
	
	private String numero_destinateur;
	
	private String devise;
	
	private int montant;
	
	
	//Generation Getters Setters
	
	

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}


	public String getNumero_debiteur() {
		return numero_debiteur;
	}

	public void setNumero_debiteur(String numero_debiteur) {
		this.numero_debiteur = numero_debiteur;
	}

	public String getNumero_destinateur() {
		return numero_destinateur;
	}

	public void setNumero_destinateur(String numero_destinateur) {
		this.numero_destinateur = numero_destinateur;
	}
	
	
	

}
