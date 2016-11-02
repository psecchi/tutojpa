package com.proxiad.formation.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class AbstractAuditableEntity {

	/**
	Montre à la fois l'heritage : 
	@MappedSuperclass ==> cette classe n'est pas une entitée : pas de table lui est associée.
	Toutes les tables associées aux entitées étendant cette classe auront les colonnes mappées dans cette classe !
	Utiles pour des données de traçabilité par ex (dateCreation, userCreation, ...) communes à plusieurs tables de votre modèle

	Et la gestion de callback sur les événements :
	les annotations @PrePersist ou @PreUpdate permettent de spécifier un comportement lors de chaque insert, update...
	 */

	@Column(name = "date_creation")
	private Date dateCreation;

	@Column(name = "date_maj")
	private Date dateMaj;

	public Date getDateCreation() {
		return dateCreation;
	}

	public Date getDateMaj() {
		return dateMaj;
	}

	@PrePersist
	void onCreate() {
		dateCreation = new Date();
		dateMaj = dateCreation;
	}

	@PreUpdate
	void onUpdate() {
		dateMaj = new Date();
	}
}
