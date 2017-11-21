package com.proxiad.formation.jpa.dto;

public class ClientDTO {

	private String nomClient;
	private String prenomClient;
	
	public ClientDTO(String nomClient, String prenomClient) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	
}
