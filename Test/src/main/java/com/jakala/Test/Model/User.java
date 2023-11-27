package com.jakala.Test.Model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User
{
	final static LocalDate NOW = LocalDate.now();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content,name,userType;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL)    
	private List<Contract> contracts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	public void addContract (Contract  c)
	{
		c.setUser(this);
		contracts.add(c);
		
	}

	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
