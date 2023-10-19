package com.chat.websocket.webcam.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MainTableMaintenance extends Auditable{

	@Column(name="CODE")
	private String code;
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name= "DESCRIPTION", length=255, nullable = false)
	private String description;
	
	@Column(name="VALID")
	private Boolean valid;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	
	
}
