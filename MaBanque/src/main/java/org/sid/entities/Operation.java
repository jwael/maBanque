package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Inheritance
@DiscriminatorColumn(name="TYPE_OP",
discriminatorType = DiscriminatorType.STRING,length=1)
public abstract class Operation implements Serializable {

	@Id@GeneratedValue
	private Long numero;
	private Date Dateoperation;
	private double Montant;
	@ManyToOne
	@JoinColumn(name="CODE_CPTE")
	private Compte compte;
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Operation(Date dateoperation, double montant, Compte compte) {
		super();
		Dateoperation = dateoperation;
		Montant = montant;
		this.compte = compte;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Date getDateoperation() {
		return Dateoperation;
	}
	public void setDateoperation(Date dateoperation) {
		Dateoperation = dateoperation;
	}
	public double getMontant() {
		return Montant;
	}
	public void setMontant(double montant) {
		Montant = montant;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
}
