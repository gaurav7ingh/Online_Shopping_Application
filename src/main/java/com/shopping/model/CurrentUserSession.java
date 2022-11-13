package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class CurrentUserSession {

	@Id
	@Column(unique = true)
	private Integer userId;
	
	@Size(max = 10)
	private String uuid;
	
	@Email
	@NotNull
	private String email;
	
	@Size(max = 20, message = "invalid role")
	private String role;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "CurrentUserSession \n [userId=" + userId + ", \n uuid=" + uuid + ", \n email=" + email + ", \n role=" + role + "]";
	}

	
	
	
	
}
