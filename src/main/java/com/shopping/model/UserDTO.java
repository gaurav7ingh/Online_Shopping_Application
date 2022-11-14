package com.shopping.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
public class UserDTO {

	@Email
	@NotNull
	private String email;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 6, max = 15, message = "please provide the currect password")
	private String password;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
