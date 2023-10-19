package com.chat.websocket.webcam.serviceimpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chat.websocket.webcam.bean.JwtDto;
import com.chat.websocket.webcam.bean.LoginUserDto;
import com.chat.websocket.webcam.bean.MainTableDto;
import com.chat.websocket.webcam.bean.UserDto;
import com.chat.websocket.webcam.entity.User;
import com.chat.websocket.webcam.mapper.UserMapper;
import com.chat.websocket.webcam.model.tablevalue.support.RoleEnum;
import com.chat.websocket.webcam.repository.UserRepository;
import com.chat.websocket.webcam.repository.tablevalue.Role;
import com.chat.websocket.webcam.security.jwt.JwtProvider;
import com.chat.websocket.webcam.service.UserService;

import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleServiceImpl roleService;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	UserMapper userMapper;

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> getAll() {
		List<UserDto> modelList = userMapper.toListResultsDto(userRepository.findAll());
		logger.debug("Get all list of users ");
		return modelList;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserDto> getOne(Long id) {
		Optional<User> model = userRepository.findById(id);
		logger.debug("Get user with id  {}", id);
		return Optional.of(userMapper.toDto(model.get()));
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		logger.debug("Delete user with id  {}", id);
		userRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserDto> getByNameUser(String nameUser) {
		return setPasswordEmpty(userRepository.findByNameUser(nameUser));
	}
	
	/*@Override
	@Transactional(readOnly = true)
	public void sendEmailTemplate(EmailValuesDto emailValuesDto) {
		Optional<UserDto> usuarioOpt = this.getByNameUserOrEmail(emailValuesDto.getUserName());
		if (!usuarioOpt.isPresent()) {
			throw new Exception("No existe el usuario");
		}

		UserDto usuario = usuarioOpt.get();
		dto.setMailFrom(mailFrom);
		dto.setSubject(subject);
		dto.setUserName(usuario.getNameUser());
		dto.setMailTo(usuario.getEmail());

		UUID uuid = UUID.randomUUID();
		String tokenPassword = uuid.toString();
		dto.setTokenPassword(tokenPassword);
		usuario.setTokenPassword(tokenPassword);
		usuarioService.saveUser(usuario);
		emailService.sendEmailTemplate(dto);
	}*/
	
	private Optional<UserDto> setPasswordEmpty(Optional<User> userOptional){
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setPassword(null); 
			return Optional.of(userMapper.toDto(user));
		} else {
			return Optional.empty();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserDto> getByNameUserOrEmail(String nameOrEmail) {
		logger.debug("Get name user or email  {}", nameOrEmail);
		return setPasswordEmpty(userRepository.findByNameUserOrEmail(nameOrEmail, nameOrEmail));
	}

	
	@Override
	@Transactional(readOnly = true)
	public Optional<UserDto> getByTokenPassword(String tokenPassword) {
		logger.debug("Get token  {}", tokenPassword);
		return setPasswordEmpty(userRepository.findByTokenPassword(tokenPassword));
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByNameUser(String nombreUsuario) {
		logger.debug("Exists username {}", nombreUsuario);
		return userRepository.existsByNameUser(nombreUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsById(Long id) {
		logger.debug("Exists user with id  {}", id);
		return userRepository.existsById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
		logger.debug("Exists email  {}", email);
		return userRepository.existsByEmail(email);
	}

	@Override
	@Transactional
	public UserDto save(UserDto newUser) {
		User user = new User();
		userMapper.dtoToModel(newUser, user);
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		List<Role> roles = new ArrayList<>();
		roles.add(roleService.getByRoleName(RoleEnum.ROLE_USER).get());
		/*
		 * if (newUser.getRoles().contains("admin")) {
		 * roles.add(roleService.getByRoleName(RoleEnum.ROLE_ADMIN).get()); }
		 */
		user.setRoles(roles);
		userRepository.save(user);
		logger.debug("Create new series with id {}", user.getId());
		return userMapper.toDto(user);
	}

	@Override
	@Transactional(readOnly = true)
	public JwtDto login(LoginUserDto loginUser) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getNameUser(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		logger.debug("User login successful with username {}", userDetails.getUsername());
		return new JwtDto(jwt);
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userRepository.save(user);
		logger.debug("Create new user with id {}", user.getId());
	}

	@Override
	@Transactional
	public JwtDto refresh(JwtDto jwtDto) throws ParseException {
		String token = jwtProvider.refreshToken(jwtDto);
		logger.debug("Refresh token {}", token);
		return new JwtDto(token);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<MainTableDto> getRoles(JwtDto jwtDto){
		logger.debug("Get roles token {}", jwtDto.getToken());
		return jwtProvider.getRoles(jwtDto);
	}
	
	
}
