package com.chat.websocket.webcam.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import com.chat.websocket.webcam.bean.MainTableDto;
import com.chat.websocket.webcam.bean.UserDto;
import com.chat.websocket.webcam.entity.User;
import com.chat.websocket.webcam.repository.RoleRepository;
import com.chat.websocket.webcam.repository.UserRepository;
import com.chat.websocket.webcam.repository.tablevalue.Role;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

	@Autowired
	private MainTableMapper mainTableMapper;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Mappings({ @Mapping(source = "user.id", target = "id"), 
			@Mapping(source = "user.name", target = "name"),
			@Mapping(source = "user.lastName1", target = "lastName1"),
			@Mapping(source = "user.lastName2", target = "lastName2"),
			@Mapping(source = "user.stateAccount", target = "stateAccount"),
			@Mapping(source = "user.language", target = "language"),
			@Mapping(source = "user.imagePerfil", target = "imagePerfil"),
			@Mapping(source = "user.nameUser", target = "nameUser"),
			@Mapping(source = "user.email", target = "email"),
			@Mapping(source = "user.password", target = "password"),
			@Mapping(source = "user.tokenPassword", target = "tokenPassword"),
			@Mapping(source = "user.createdBy", target = "createdBy"),
			@Mapping(source = "user.createdDate", target = "createdDate"),
			@Mapping(source = "user.lastMofifiedDate", target = "lastMofifiedDate"),
			@Mapping(source = "user.modifiedBy", target = "modifiedBy"),
			@Mapping(target = "roles", ignore = true), })
	public abstract UserDto simpleToDto(User user);

	public UserDto toDto(User user) {
		UserDto userDto = this.simpleToDto(user);
		userDto.setRoles(
				user.getRoles() != null ? mainTableMapper.toDto(user.getRoles()) : new ArrayList<MainTableDto>());
		return userDto;
	}
	
	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "name", target = "name"),
		@Mapping(source = "lastName1", target = "lastName1"),
		@Mapping(source = "lastName2", target = "lastName2"),
		@Mapping(source = "stateAccount", target = "stateAccount"),
		@Mapping(source = "language", target = "language"),
		@Mapping(source = "imagePerfil", target = "imagePerfil"),
		@Mapping(source = "nameUser", target = "nameUser"),
		@Mapping(source = "email", target = "email"),
		@Mapping(source = "password", target = "password"),
		@Mapping(source = "tokenPassword", target = "tokenPassword"),
		@Mapping(source = "createdBy", target = "createdBy"),
		@Mapping(source = "createdDate", target = "createdDate"),
		@Mapping(source = "lastMofifiedDate", target = "lastMofifiedDate"),
		@Mapping(source = "modifiedBy", target = "modifiedBy"),
		@Mapping(target = "roles", ignore = true),
	})
	public abstract void simpleDtoToModel(UserDto dto, @MappingTarget User model);
	
	public void dtoToModel(UserDto dto, User model) {
		this.simpleDtoToModel(dto,  model);
		List<Role> roles = new ArrayList<Role>(); 
		if  (dto.getRoles()!=null) {
			for (MainTableDto mainTableDto : dto.getRoles()) {
				roles.add(roleRepository.findById(mainTableDto.getId()).get());
			}
		}
		model.setRoles(roles);
	}

	
	public List<UserDto> toListResultsDto(List<User> result){
		List<UserDto> resultDto = new ArrayList<UserDto>();
		for (User model:result) {
			resultDto.add(toDto(model));
		}
		return resultDto;
	}
}
