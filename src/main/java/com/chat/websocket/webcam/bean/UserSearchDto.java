package com.chat.websocket.webcam.bean;

import java.util.Date;

public class UserSearchDto {
	private String name;
	private String nameUser;
	private String email;
	protected Date createdDate;

	public UserSearchDto() {
	}

	public UserSearchDto(String name, String nameUser, String email, Date createdDate) {
		super();
		this.name = name;
		this.nameUser = nameUser;
		this.email = email;
		this.createdDate = createdDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
