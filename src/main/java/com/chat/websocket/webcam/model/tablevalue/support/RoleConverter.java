package com.chat.websocket.webcam.model.tablevalue.support;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<RoleEnum, String>{
	
	public String convertToDatabaseColumn(RoleEnum attribute) {
		return attribute != null ? attribute.getCode() : null;
	}
	
	public RoleEnum convertToEntityAttribute(String codi) {
		return RoleEnum.fromValue(codi);
	}
}
