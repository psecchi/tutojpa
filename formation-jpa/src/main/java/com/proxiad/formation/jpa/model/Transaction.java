package com.proxiad.formation.jpa.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_bancaire")
@DiscriminatorColumn(name="type")
public abstract class Transaction {

	@Id
	private String id;
	
	private Double montant;
	
//	@Column(name = "type", insertable=false, updatable=false)
//	@Enumerated(EnumType.STRING)
//	private TypeTransaction type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}
	
	
//
//	public TypeTransaction getType() {
//		return type;
//	}
//
//	public void setType(TypeTransaction type) {
//		this.type = type;
//	}
//	
	
	
}
