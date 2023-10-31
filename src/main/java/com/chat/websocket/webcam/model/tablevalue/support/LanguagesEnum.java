package com.chat.websocket.webcam.model.tablevalue.support;

import com.chat.websocket.webcam.model.base.TableValueEnum;

public enum LanguagesEnum implements TableValueEnum{
	
	ROLE_ADMIN("ROLE_ADMIN"), 
	ROLE_USER("ROLE_USER");
	
	private String code;
	
	private LanguagesEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static LanguagesEnum fromValue(String code) {
		for(LanguagesEnum value: LanguagesEnum.values()) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return null;
	}
	
}