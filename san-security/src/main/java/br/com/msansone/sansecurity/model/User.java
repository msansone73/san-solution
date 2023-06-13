package br.com.msansone.sansecurity.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email
	private String email;
	@NotBlank
	private String pass;
	@NotBlank
	private String name;
	private LocalDate dateCreate;
	private boolean admin;
	private boolean enable=true;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(@Email String email, @NotBlank String pass, @NotBlank String name, LocalDate dateCreate, boolean admin,
			boolean enable) {
		super();
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.dateCreate = dateCreate;
		this.admin = admin;
		this.enable = enable;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getDateCreate() {
		return dateCreate;
	}


	public void setDateCreate(LocalDate dateCreate) {
		this.dateCreate = dateCreate;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	
	
	
}
