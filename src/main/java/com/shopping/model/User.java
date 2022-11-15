package com.shopping.model;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
public class User {

	private Integer userId;

	@Email(message = "please provide the correct email")
	@NotNull(message = "please provide the email...!")
	@Column(unique = true)
	private String email;

	@NotNull
	@Size(min = 3, max = 20, message = "firstname should be greater then 3 and less the 20")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 20, message = "lastname should be greater then 2 and less the 20")
	private String lastName;

	@NotNull(message = "please provide the mobile number")
	@Size(min = 10, max = 10, message = "your mobile number length not appropreate")
	@Column(unique = true)
	private String mobileNumber;

	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min = 6, max = 15, message = "the password length is not apropriate")
	private String password;

	@NotNull
	@Size(min = 5, max = 8, message = "please privide role (admin or customer)")
	private String role;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

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