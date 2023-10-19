package com.chat.websocket.webcam.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TableValue <T extends TableValueEnum> implements MasterTable{

	@Column(name="DESCRIPTION")
	protected String description;
	
	
	@Column(name= "VALID")
	private Boolean valid;
	
	public abstract T getCode();
	
	public abstract void setCode(T code);

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
