package com.Api_Parking.Model;

public class RequestToPayObjectSuccess {
	
	private String financialTransactionId;
	
	private String externalId;
	
	private String amount;
	
	private String currency;
	
	private Payer payer;
	
	private String payerMessage;
	
	private String payeeNote;
	
	private String status;
	
	

	public String getFinancialTransactionId() {
		return financialTransactionId;
	}

	public void setFinancialTransactionId(String financialTransactionId) {
		this.financialTransactionId = financialTransactionId;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Payer getPayer() {
		return payer;
	}

	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	public String getPayerMessage() {
		return payerMessage;
	}

	public void setPayerMessage(String payerMessage) {
		this.payerMessage = payerMessage;
	}

	public String getPayeeNote() {
		return payeeNote;
	}

	public void setPayeeNote(String payeeNote) {
		this.payeeNote = payeeNote;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	
	
		

}
