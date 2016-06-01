package com.proxiad.formation.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ligne_commande")
@IdClass(LigneCommandePK.class)
public class LigneCommande implements Serializable {

	private static final long serialVersionUID = -7633597732765228214L;

	@Id
	@Column(name = "numero_ligne")
	private Integer numeroLigne;

	@ManyToOne
	@JoinColumn(name = "commande_id", foreignKey = @ForeignKey(name = "fk_ligne_commande_commande"))
	@Id
	private Commande commande;

	@ManyToOne
	@JoinColumn(name = "article_code", foreignKey = @ForeignKey(name = "fk_ligne_commande_article"))
	private Article article;

	@Column
	private Integer quantite;

	public Integer getNumeroLigne() {
		return numeroLigne;
	}

	public void setNumeroLigne(Integer numeroLigne) {
		this.numeroLigne = numeroLigne;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
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
		LigneCommande other = (LigneCommande) obj;
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
