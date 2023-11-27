package com.jakala.Test.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contract 
{
	final static LocalDate NOW = LocalDate.now();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String contracttype;
	private LocalDate createdOn;
	private LocalDate LastmodifiedOn;
	
	
	
	 @ManyToOne
	 @JoinColumn(name = "UserID") 
	 private User user;   

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContracttype() {
		return contracttype;
	}
	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDate getLastModifiedOn() {
		return LastmodifiedOn;
	}
	public void setLastModifiedOn(LocalDate lastModifiedOn) {
		LastmodifiedOn = lastModifiedOn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
}
