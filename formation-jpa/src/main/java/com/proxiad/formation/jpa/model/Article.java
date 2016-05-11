package com.proxiad.formation.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Article {

	@Id
	private Integer code;
	
	@Column
	private String designation;
	
	@Column
	private Double prix;
}
