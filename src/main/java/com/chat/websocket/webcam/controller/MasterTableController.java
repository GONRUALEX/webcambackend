package com.chat.websocket.webcam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.websocket.webcam.bean.MainTableDto;
import com.chat.websocket.webcam.service.MasterTableService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/api/mastertable")
public class MasterTableController  {
	
	@Autowired
	private MasterTableService service;
	
	@GetMapping(value = "/roles")
	@ResponseBody
	public ResponseEntity<List<MainTableDto>> getRoles() {
		List<MainTableDto> results = service.getRoles();
		return prepareResponse(results);
	}
	
	@GetMapping(value = "/languages")
	@ResponseBody
	public ResponseEntity<List<MainTableDto>> getLanguages() {
		List<MainTableDto> results = service.getLanguages();
		return prepareResponse(results);
	}
	
	private ResponseEntity<List<MainTableDto>> prepareResponse(List<MainTableDto> results){
		return ResponseEntity.ok().body(results);
	}
	
}
