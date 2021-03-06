package com.proxiad.formation.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Commande implements Serializable {

	private static final long serialVersionUID = 457701419354044554L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "date_creation")
	private Date dateCreation;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EtatCommande etat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_numero", nullable = false, foreignKey = @ForeignKey(name = "fk_commande_client"))
	private Client client;

	// @formatter:off
	// FIXME : à supprimer et à remplacer par un @OneToMany sur LigneCommande
		// car la table d'association Commande/Article va porter des infos
		// supplémentaires (quantité, numéro de ligne...)
	/*
	@ManyToMany
	@JoinTable(name = "commande_details", 
			   joinColumns = {@JoinColumn(name = "commande_id", referencedColumnName = "id") }, 
			   inverseJoinColumns = {@JoinColumn(name = "article_code", referencedColumnName = "code") })
	
	// @formatter:on
	private Set<Article> articles = new HashSet<>();
	*/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LigneCommande> lignes = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public EtatCommande getEtat() {
		return etat;
	}

	public void setEtat(EtatCommande etat) {
		this.etat = etat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

//	public Set<Article> getArticles() {
//		return articles;
//	}
//
//	public void setArticles(Set<Article> articles) {
//		this.articles = articles;
//	}

	public Set<LigneCommande> getLignes() {
		return lignes;
	}

	public void setLignes(Set<LigneCommande> lignes) {
		this.lignes = lignes;
	}
	
	public void addLigne(LigneCommande ligne) {
		ligne.setCommande(this);
		this.getLignes().add(ligne);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
