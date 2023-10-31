package com.chat.websocket.webcam.service;

import java.util.Optional;

import com.chat.websocket.webcam.model.tablevalue.support.RoleEnum;
import com.chat.websocket.webcam.model.taulavalor.Role;

public interface RoleService {

	public Role getByRoleName(RoleEnum code);

	public void save(Role role);

}
