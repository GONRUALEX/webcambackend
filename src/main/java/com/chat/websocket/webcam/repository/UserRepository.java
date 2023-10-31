package com.chat.websocket.webcam.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chat.websocket.webcam.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	@Query(value =
			  "SELECT user FROM User user "
			+ "WHERE 1 = 1 "
			+ "AND (:name IS NULL OR UPPER(user.name)  LIKE '%'||UPPER(:name)||'%') "
			+ "AND (:nameUser IS NULL OR UPPER(user.nameUser)  LIKE '%'||UPPER(:nameUser)||'%') "
			+ "AND (:email IS NULL OR UPPER(user.email)  LIKE '%'||UPPER(:email)||'%') "
			+ "AND (:createdDate IS NULL OR user.createdDate >= :createdDate) "
		  )
	Page<User> findAllUser(
		@Param("name") String name,
		@Param("nameUser") String nameUser, 
		@Param("email") String email,
		@Param("createdDate") Date createdDate, 
		Pageable pageable);

	Page<User> findAll(Pageable pageable);
	
	Optional<User> findByNameUser(String nameUser);

	Optional<User> findByNameUserOrEmail(String nameUser, String email);

	Optional<User> findByTokenPassword(String tokenPassword);

	boolean existsByNameUser(String nameUser);

	boolean existsByEmail(String email);

}
