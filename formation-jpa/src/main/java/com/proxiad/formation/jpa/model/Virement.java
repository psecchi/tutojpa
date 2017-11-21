package com.proxiad.formation.jpa.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VIREMENT")
public class Virement extends Transaction {

	@Column(name="compte_destinataire")
	private String compteDestinataire;

	public String getCompteDestinataire() {
		return compteDestinataire;
	}

	public void setCompteDestinataire(String compteDestinataire) {
		this.compteDestinataire = compteDestinataire;
	}
	
	
	
}
