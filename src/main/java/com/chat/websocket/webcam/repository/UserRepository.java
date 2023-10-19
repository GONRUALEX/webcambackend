package com.chat.websocket.webcam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.websocket.webcam.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAll();
	
	Optional<User> findByNameUser(String nameUser);

	Optional<User> findByNameUserOrEmail(String nameUser, String email);

	Optional<User> findByTokenPassword(String tokenPassword);

	boolean existsByNameUser(String nameUser);

	boolean existsByEmail(String email);

}
