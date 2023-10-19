package com.chat.websocket.webcam.model.tablevalue.support;

import com.chat.websocket.webcam.model.base.TableValueEnum;

public enum RoleEnum implements TableValueEnum{
		
		ROLE_ADMIN("ROLE_ADMIN"), 
		ROLE_USER("ROLE_USER");
		
		private String codi;
		
		private RoleEnum(String codi) {
			this.codi = codi;
		}
		
		public String getCode() {
			return codi;
		}
		
		public static RoleEnum fromValue(String codi) {
			for(RoleEnum value: RoleEnum.values()) {
				if (value.getCode().equals(codi)) {
					return value;
				}
			}
			return null;
		}
		
	}
