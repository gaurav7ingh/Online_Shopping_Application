package com.shopping.model;

import java.time.LocalDateTime;

public class ShoppingError {

	private LocalDateTime timestamp;
	private String message;
	private String description;
	private String path;

	public String getDescription() {
		return description;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
