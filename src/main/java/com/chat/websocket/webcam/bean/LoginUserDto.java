package com.chat.websocket.webcam.bean;

import jakarta.validation.constraints.NotBlank;

public class LoginUserDto {
	@NotBlank
	private String nameUser;
	@NotBlank
	private String password;

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
