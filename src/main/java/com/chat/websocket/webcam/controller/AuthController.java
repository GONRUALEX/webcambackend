package com.chat.websocket.webcam.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.websocket.webcam.bean.JwtDto;
import com.chat.websocket.webcam.bean.LoginUserDto;
import com.chat.websocket.webcam.bean.MainTableDto;
import com.chat.websocket.webcam.bean.MensajeDto;
import com.chat.websocket.webcam.bean.UserDto;
import com.chat.websocket.webcam.serviceimpl.UserServiceImpl;

import jakarta.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

	@Autowired
	UserServiceImpl userService;

	@PostMapping("/new")
	public ResponseEntity<?> nuevo(@Valid @RequestBody UserDto nuevoUsuario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new MensajeDto("Campos incorrectos"), HttpStatus.BAD_REQUEST);
		}
		if (userService.existsByNameUser(nuevoUsuario.getNameUser())) {
			return new ResponseEntity(new MensajeDto("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		}
		if (userService.existsByEmail(nuevoUsuario.getEmail())) {
			return new ResponseEntity(new MensajeDto("Email ya existe"), HttpStatus.BAD_REQUEST);
		}

		userService.save(nuevoUsuario);
		return new ResponseEntity(new MensajeDto("Usuario guardado"), HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUserDto loginUsuario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new MensajeDto("Campos incorrectos"), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity(userService.login(loginUsuario), HttpStatus.OK);

	}
	
	@PostMapping("/roles")
	public ResponseEntity<List<MainTableDto>> Roles(@RequestBody JwtDto jwtDto) {
		return new ResponseEntity(userService.getRoles(jwtDto), HttpStatus.OK);

	}

	@PostMapping("/refresh")
	public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
		return new ResponseEntity(userService.refresh(jwtDto), HttpStatus.OK);

	}
	
	/*
	@GetMapping("/csrf")
	@CrossOrigin(origins = "*")
    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }
    */
}
