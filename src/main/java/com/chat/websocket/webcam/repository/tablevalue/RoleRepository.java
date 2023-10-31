package com.chat.websocket.webcam.repository.tablevalue;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chat.websocket.webcam.model.tablevalue.support.RoleEnum;
import com.chat.websocket.webcam.model.taulavalor.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findAllByOrderByDescriptionAsc();

	List<Role> findAll();
	
	Role getByCode(RoleEnum code);
	
	String getCodeById(Long id);
	
	Long getIdByCode(String codi);
	
	@Query(value = "SELECT rol FROM Role rol WHERE 1 = 1 AND :id IS NULL OR rol.id = :id")
	Role findOne(@Param("id") Long id);
}
