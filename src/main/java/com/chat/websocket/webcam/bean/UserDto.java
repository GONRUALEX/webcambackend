package com.chat.websocket.webcam.bean;

import java.util.Date;
import java.util.List;

public class UserDto {

	private Long id;
	private String name;
	private String lastName1;
	private String lastName2;
	private Boolean stateAccount;
	private String language;
	private byte[] imagePerfil;
	private String nameUser;
	private String email;
	private String password;
	private String tokenPassword;
	private List<MainTableDto> roles;
	protected String createdBy;
	protected Date createdDate;
	protected Date lastMofifiedDate;
	protected String modifiedBy;

	public UserDto() {
	}
	

	public UserDto(Long id, String name, String lastName1, String lastName2, Boolean stateAccount, String language,
			byte[] imagePerfil, String nameUser, String email, String password, String tokenPassword,
			List<MainTableDto> roles, String createdBy, Date createdDate, Date lastMofifiedDate, String modifiedBy) {
		super();
		this.id = id;
		this.name = name;
		this.lastName1 = lastName1;
		this.lastName2 = lastName2;
		this.stateAccount = stateAccount;
		this.language = language;
		this.imagePerfil = imagePerfil;
		this.nameUser = nameUser;
		this.email = email;
		this.password = password;
		this.tokenPassword = tokenPassword;
		this.roles = roles;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastMofifiedDate = lastMofifiedDate;
		this.modifiedBy = modifiedBy;
	}


	public UserDto(String name, String nameUser, String email, String password) {

		this.name = name;
		this.nameUser = nameUser;
		this.email = email;
		this.password = password;
		this.stateAccount = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setNNameUser(String nameUser) {
		this.nameUser = nameUser;
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

	public List<MainTableDto> getRoles() {
		return roles;
	}

	public void setRoles(List<MainTableDto> roles) {
		this.roles = roles;
	}

	public String getTokenPassword() {
		return tokenPassword;
	}

	public void setTokenPassword(String tokenPassword) {
		this.tokenPassword = tokenPassword;
	}

	public String getLastName1() {
		return lastName1;
	}

	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	public Boolean getStateAccount() {
		return stateAccount;
	}

	public void setStateAccount(Boolean stateAccount) {
		this.stateAccount = stateAccount;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public byte[] getImagePerfil() {
		return imagePerfil;
	}

	public void setImagePerfil(byte[] imagePerfil) {
		this.imagePerfil = imagePerfil;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastMofifiedDate() {
		return lastMofifiedDate;
	}

	public void setLastMofifiedDate(Date lastMofifiedDate) {
		this.lastMofifiedDate = lastMofifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
