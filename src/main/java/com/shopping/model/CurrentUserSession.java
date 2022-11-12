package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class CurrentUserSession {

	@Id
	@Column(unique = true)
	private Integer userId;
	
	private String uuid;
	
	private java.time.LocalDateTime LocalDateTime;

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

	public java.time.LocalDateTime getLocalDateTime() {
		return LocalDateTime;
	}

	public void setLocalDateTime(java.time.LocalDateTime localDateTime) {
		LocalDateTime = localDateTime;
	}

	
	
	
	
}
