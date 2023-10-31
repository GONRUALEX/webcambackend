package com.chat.websocket.webcam.model.tablevalue.support;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LanguagesConverter implements AttributeConverter<LanguagesEnum, String>{
	
	public String convertToDatabaseColumn(LanguagesEnum attribute) {
		String valor = attribute.getCode();
		return attribute != null ? attribute.getCode() : null;
	}
	
	public LanguagesEnum convertToEntityAttribute(String code) {
		return LanguagesEnum.fromValue(code);
	}
}