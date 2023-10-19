package com.chat.websocket.webcam.service;

import java.util.Optional;

import com.chat.websocket.webcam.model.tablevalue.support.RoleEnum;
import com.chat.websocket.webcam.repository.tablevalue.Role;

public interface RoleService {

	public Optional<Role> getByRoleName(RoleEnum codi);

	public void save(Role role);

}
