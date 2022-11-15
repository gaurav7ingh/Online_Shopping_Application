package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CurrentUserSession {

	@Id
	@Column(unique = true)
	private Integer userId;

	@Size(max = 10)
	private String uuid;

	@NotNull
	private Integer cartId;

	@Size(max = 20, message = "invalid role")
	private String role;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getRole() {
		return role;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "CurrentUserSession \n [userId=" + userId + ", \n uuid=" + uuid + ", \n role=" + role + "]";
	}

}
