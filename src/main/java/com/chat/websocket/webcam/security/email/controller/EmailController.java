package com.chat.websocket.webcam.security.email.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chat.websocket.webcam.bean.MensajeDto;
import com.chat.websocket.webcam.bean.UserDto;
import com.chat.websocket.webcam.entity.User;
import com.chat.websocket.webcam.security.email.dto.ChangePasswordDto;
import com.chat.websocket.webcam.security.email.dto.EmailValuesDto;
import com.chat.websocket.webcam.security.email.service.EmailService;
import com.chat.websocket.webcam.serviceimpl.UserServiceImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/email-password")
@CrossOrigin
public class EmailController {

	@Autowired
	EmailService emailService;

	@Autowired
	UserServiceImpl usuarioService;

	@Value("${spring.mail.mailFrom}")
	private String mailFrom;

	@Autowired
	PasswordEncoder passwordEncoder;

	private static final String subject = "cambio de contraseña";

	/*@PostMapping("/send-email")
	public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDto dto) {
		try {
			usuarioService.sendEmailTemplate(dto);
		}catch(Exception e) {
			 new ResponseEntity(new MensajeDto("No existe ningún usuario con ese nombre"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(new MensajeDto("Enviado correo"), HttpStatus.OK);
	}

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDto dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new MensajeDto("Campos mal puestos"), HttpStatus.BAD_REQUEST);
		}
		if (!dto.getPassword().equals(dto.getConfirmPassword())) {
			return new ResponseEntity(new MensajeDto("Contraseñas no coinciden"), HttpStatus.BAD_REQUEST);
		}
		Optional<UserDto> usuarioOpt = usuarioService.getByTokenPassword(dto.getTokenPassword());
		if (!usuarioOpt.isPresent()) {
			return new ResponseEntity(new MensajeDto("No existe ningún usuario con estas crendenciales"),
					HttpStatus.BAD_REQUEST);
		}
		UserDto usuario = usuarioOpt.get();
		String password = passwordEncoder.encode(dto.getPassword());
		usuario.setPassword(password);
		usuario.setTokenPassword(null);
		usuarioService.saveUser(usuario);
		return new ResponseEntity(new MensajeDto("Contraseña actualizada con éxito!"), HttpStatus.OK);
	}*/

}