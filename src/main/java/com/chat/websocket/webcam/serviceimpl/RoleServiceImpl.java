package com.chat.websocket.webcam.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.websocket.webcam.model.tablevalue.support.RoleEnum;
import com.chat.websocket.webcam.repository.RoleRepository;
import com.chat.websocket.webcam.repository.tablevalue.Role;
import com.chat.websocket.webcam.service.RoleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;

	public Optional<Role> getByRoleName(RoleEnum codi) {
		return roleRepository.findByCode(codi);
	}

	public void save(Role role) {
		roleRepository.save(role);
	}

}
