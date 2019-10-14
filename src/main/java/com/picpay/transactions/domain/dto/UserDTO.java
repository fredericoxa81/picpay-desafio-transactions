package com.picpay.transactions.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@JsonProperty("full_name")
	private String fullName;
	
	private String cpf;

	@JsonProperty("phone_number")
	private String phoneNumber;
	
	private String email;
	
	private String password;
	
	public UserDTO() {
		
	}
	
	public UserDTO(Integer id, String fullName, String cpf, String phoneNumber, String email, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.cpf = cpf;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
