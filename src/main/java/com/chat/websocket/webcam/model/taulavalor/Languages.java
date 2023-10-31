package com.chat.websocket.webcam.model.taulavalor;

import com.chat.websocket.webcam.model.base.TableValue;
import com.chat.websocket.webcam.model.tablevalue.support.LanguagesConverter;
import com.chat.websocket.webcam.model.tablevalue.support.LanguagesEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LANGUAGES")
public class Languages extends TableValue<LanguagesEnum> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Convert(converter = LanguagesConverter.class)
	@Column(name = "CODE")
	private LanguagesEnum code;

	public Languages() {}
	
	public Languages(LanguagesEnum languagesEnum) {
		this.code = languagesEnum;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LanguagesEnum getCode() {
		return code;
	}

	public void setCode(LanguagesEnum code) {
		this.code = code;
	}

	@Override
	public void setId() {
		// TODO Auto-generated method stub
		
	}

	
}