package com.chat.websocket.webcam.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chat.websocket.webcam.model.base.Auditable;
import com.chat.websocket.webcam.model.taulavalor.Languages;
import com.chat.websocket.webcam.model.taulavalor.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
public class User extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@Lob
	@Column(columnDefinition = "BLOB")
	private byte[] imagePerfil;
	@NotNull
	@Column(unique = true)
	private String nameUser;
	@NotNull
	private String email;
	@NotNull
	private String password;
	private String tokenPassword;
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

	private String lastName1;
	private String lastName2;
	private Boolean stateAccount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "language")
	private Languages language;
	
	public User() {
		super();
	}

	public User(String name, String nameUser, String email, String password) {
		super();
		this.name = name;
		this.nameUser = nameUser;
		this.email = email;
		this.password = password;
		this.stateAccount = true;
	}
	
	

	public User(Long id,  String name, String lastName1, String lastName2, Boolean stateAccount,
			Languages language, byte[] imagePerfil, String nameUser, String email,
			 String password, String tokenPassword, List<Role> roles) {
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
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

	public Languages getLanguage() {
		return language;
	}

	public void setLanguage(Languages language) {
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

}
