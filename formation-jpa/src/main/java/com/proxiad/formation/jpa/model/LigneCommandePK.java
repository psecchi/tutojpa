package com.proxiad.formation.jpa.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class LigneCommandePK implements Serializable {

	private static final long serialVersionUID = 5369277290873581904L;

	private Integer numeroLigne;
	private String commande;

	public Integer getNumeroLigne() {
		return numeroLigne;
	}

	public void setNumeroLigne(Integer numeroLigne) {
		this.numeroLigne = numeroLigne;
	}

	public String getCommande() {
		return commande;
	}

	public void setCommande(String commande) {
		this.commande = commande;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commande == null) ? 0 : commande.hashCode());
		result = prime * result + ((numeroLigne == null) ? 0 : numeroLigne.hashCode());
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
		LigneCommandePK other = (LigneCommandePK) obj;
		if (commande == null) {
			if (other.commande != null)
				return false;
		} else if (!commande.equals(other.commande))
			return false;
		if (numeroLigne == null) {
			if (other.numeroLigne != null)
				return false;
		} else if (!numeroLigne.equals(other.numeroLigne))
			return false;
		return true;
	}

}
