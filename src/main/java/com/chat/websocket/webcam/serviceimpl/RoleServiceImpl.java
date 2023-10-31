package com.chat.websocket.webcam.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.websocket.webcam.model.tablevalue.support.RoleEnum;
import com.chat.websocket.webcam.model.taulavalor.Role;
import com.chat.websocket.webcam.repository.tablevalue.RoleRepository;
import com.chat.websocket.webcam.service.RoleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;

	public Role getByRoleName(RoleEnum code) {
		Role rol = roleRepository.getByCode(code);	
		return rol;	
		}

	public void save(Role role) {
		roleRepository.save(role);
	}

}
