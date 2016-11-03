package com.proxiad.formation.jpa.dto;

import java.io.Serializable;
import java.util.Date;

import com.proxiad.formation.jpa.model.EtatCommande;

public class CommandeClientDTO implements Serializable {

	private static final long serialVersionUID = 3535361924604969890L;

	private String nomClient;

	private String prenomClient;

	private String idCommande;

	private EtatCommande etatCommande;

	private Date dateCommande;

	private Integer quantite;

	private Integer codeArticle;
	
	private String designationArticle;

	private Double prixUnitaire;

	private Double montantLigne;

	public CommandeClientDTO(String nomClient, String prenomClient, String idCommande, EtatCommande etatCommande,
			Date dateCommande, Integer quantite, Integer codeArticle, String designationArticle, Double prixUnitaire,
			Double montantLigne) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.idCommande = idCommande;
		this.etatCommande = etatCommande;
		this.dateCommande = dateCommande;
		this.quantite = quantite;
		this.codeArticle = codeArticle;
		this.designationArticle = designationArticle;
		this.prixUnitaire = prixUnitaire;
		this.montantLigne = montantLigne;
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

	public String getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(String idCommande) {
		this.idCommande = idCommande;
	}

	public EtatCommande getEtatCommande() {
		return etatCommande;
	}

	public void setEtatCommande(EtatCommande etatCommande) {
		this.etatCommande = etatCommande;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Integer getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(Integer codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getDesignationArticle() {
		return designationArticle;
	}

	public void setDesignationArticle(String designationArticle) {
		this.designationArticle = designationArticle;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getMontantLigne() {
		return montantLigne;
	}

	public void setMontantLigne(Double montantLigne) {
		this.montantLigne = montantLigne;
	}

	@Override
	public String toString() {
		return "CommandeClientDTO [nomClient=" + nomClient + ", prenomClient=" + prenomClient + ", idCommande="
				+ idCommande + ", etatCommande=" + etatCommande + ", dateCommande=" + dateCommande + ", quantite="
				+ quantite + ", codeArticle=" + codeArticle + ", designationArticle=" + designationArticle
				+ ", prixUnitaire=" + prixUnitaire + ", montantLigne=" + montantLigne + "]";
	}
	
	
	
	

}
