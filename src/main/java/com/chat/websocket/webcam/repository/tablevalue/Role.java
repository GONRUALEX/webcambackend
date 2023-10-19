package com.chat.websocket.webcam.repository.tablevalue;

import com.chat.websocket.webcam.model.base.TableValue;
import com.chat.websocket.webcam.model.tablevalue.support.RoleConverter;
import com.chat.websocket.webcam.model.tablevalue.support.RoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role extends TableValue<RoleEnum> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Convert(converter = RoleConverter.class)
	@Column(name = "CODE")
	private RoleEnum code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleEnum getCode() {
		return code;
	}

	public void setCode(RoleEnum code) {
		this.code = code;
	}

	@Override
	public void setId() {
		// TODO Auto-generated method stub
		
	}

	
}
