package com.chat.websocket.webcam.repository.tablevalue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chat.websocket.webcam.model.tablevalue.support.LanguagesEnum;
import com.chat.websocket.webcam.model.taulavalor.Languages;

public interface LanguagesRepository extends JpaRepository<Languages, Long> {
	List<Languages> findAllByOrderByDescriptionAsc();

	List<Languages> findAll();
	
	Languages getByCode(LanguagesEnum code);
	
	String getCodeById(Long id);
	
	Long getIdByCode(String codi);
	
	@Query(value = "SELECT lang FROM Languages lang WHERE 1 = 1 AND :id IS NULL OR lang.id = :id")
	Languages findOne(@Param("id") Long id);
	
}