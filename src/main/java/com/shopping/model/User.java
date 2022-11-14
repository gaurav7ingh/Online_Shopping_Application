package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@Email(message = "please provide the correct email")
	@NotNull(message = "please provide the email...!")
	@Column(unique = true)
	private String email;

	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min = 6, max = 15, message = "the password length is not apropriate")
	private String password;

	@NotNull
	@Size(min = 5, max = 8, message = "please privide role (admin or customer)")
	private String role;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}