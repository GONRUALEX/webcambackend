package com.chat.websocket.webcam.model.tablevalue.support;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<RoleEnum, String>{
	
	public String convertToDatabaseColumn(RoleEnum attribute) {
		String valor = attribute.getCode();
		return attribute != null ? attribute.getCode() : null;
	}
	
	public RoleEnum convertToEntityAttribute(String code) {
		return RoleEnum.fromValue(code);
	}
}
