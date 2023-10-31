package com.chat.websocket.webcam.model.tablevalue.support;

import com.chat.websocket.webcam.model.base.TableValueEnum;

public enum RoleEnum implements TableValueEnum{
		
		ROLE_ADMIN("ROLE_ADMIN"), 
		ROLE_USER("ROLE_USER");
		
		private String code;
		
		private RoleEnum(String code) {
			this.code = code;
		}
		
		public String getCode() {
			return code;
		}
		
		public static RoleEnum fromValue(String code) {
			for(RoleEnum value: RoleEnum.values()) {
				if (value.getCode().equals(code)) {
					return value;
				}
			}
			return null;
		}
		
	}
