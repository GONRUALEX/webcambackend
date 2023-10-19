package com.chat.websocket.webcam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chat.websocket.webcam.bean.MensajeDto;
import com.chat.websocket.webcam.bean.UserDto;
import com.chat.websocket.webcam.service.UserService;

@Controller
@RequestMapping("/api/users")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	@Autowired
	UserService userService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<UserDto>> getAll() {
		List<UserDto> list = userService.getAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
		if (!userService.existsById(id))
			return new ResponseEntity(new MensajeDto("No existe el usuario"), HttpStatus.NOT_FOUND);
		UserDto userDto = userService.getOne(id).get();
		return new ResponseEntity(userDto, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detailname/{name}")
	public ResponseEntity<UserDto> getByName(@PathVariable("name") String name) {
		if (!userService.existsByNameUser(name))
			return new ResponseEntity(new MensajeDto("No existe el usuario "), HttpStatus.NOT_FOUND);
		UserDto userDto = userService.getByNameUser(name).get();
		return new ResponseEntity(userDto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<UserDto> create(@RequestBody UserDto userDto, BindingResult bindingResult) {
		if (StringUtils.isBlank(userDto.getName())) {
			logger.error("Create user -> Error create user, name is blank {}", userDto.toString());
			return new ResponseEntity(new MensajeDto("El nombre está en blanco"), HttpStatus.BAD_REQUEST);
		}

		if (bindingResult.hasErrors()) {
			logger.error("Create user -> Error create user, {}", userDto.toString());
			return new ResponseEntity(new MensajeDto("Campos incorrectos"), HttpStatus.BAD_REQUEST);
		}

		if (userService.existsByNameUser(userDto.getNameUser())) {
			logger.error("Create user -> Already exists name in bd user {}", userDto.toString());
			return new ResponseEntity(new MensajeDto("Ya existe el nombre "), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(userService.save(userDto), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteSerie(@PathVariable Long id){
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
