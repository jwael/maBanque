package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE",
discriminatorType = DiscriminatorType.STRING,length=2)
public abstract class Compte implements Serializable{
	@Id
	private String CodeCompte;
	private Date DateCreation;
	private double solde;
	@ManyToOne
	@JoinColumn(name="CODE_CLI")
	private Client client;
	@OneToMany(mappedBy="compte")
	private Collection<Operation> operations;
	
	

	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Compte(String codeCompte, Date dateCreation, double solde, Client client) {
		super();
		CodeCompte = codeCompte;
		DateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
	}



	public String getCodeCompte() {
		return CodeCompte;
	}



	public void setCodeCompte(String codeCompte) {
		CodeCompte = codeCompte;
	}



	public Date getDateCreation() {
		return DateCreation;
	}



	public void setDateCreation(Date dateCreation) {
		DateCreation = dateCreation;
	}



	public double getSolde() {
		return solde;
	}



	public void setSolde(double solde) {
		this.solde = solde;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}



	public Collection<Operation> getOperations() {
		return operations;
	}



	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	
	
	
	
	

}
