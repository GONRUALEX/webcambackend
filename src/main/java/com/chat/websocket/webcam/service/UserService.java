package com.chat.websocket.webcam.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.chat.websocket.webcam.bean.JwtDto;
import com.chat.websocket.webcam.bean.LoginUserDto;
import com.chat.websocket.webcam.bean.MainTableDto;
import com.chat.websocket.webcam.bean.SearchFilteringDto;
import com.chat.websocket.webcam.bean.UserDto;
import com.chat.websocket.webcam.bean.UserSearchDto;
import com.chat.websocket.webcam.entity.User;

public interface UserService {

	public Optional<UserDto> getOne(Long id);
	
	public Page<UserDto> getAll(SearchFilteringDto<UserSearchDto> search);;
	
	public void deleteById(Long id);

	public Optional<UserDto> getByNameUser(String nameUser);

	public Optional<UserDto> getByNameUserOrEmail(String nameOrEmail);

	public Optional<UserDto> getByTokenPassword(String tokenPassword);

	public boolean existsByNameUser(String nameUser);
	
	public boolean existsById(Long id);

	public boolean existsByEmail(String email);

	public UserDto save(UserDto newUser);
	
	public UserDto update(UserDto newUser);

	public JwtDto login(LoginUserDto loginUser);

	public void saveUser(User user);

	public JwtDto refresh(JwtDto jwtDto) throws ParseException;
	
	public List<MainTableDto>getRoles(JwtDto jwtDto);
}
