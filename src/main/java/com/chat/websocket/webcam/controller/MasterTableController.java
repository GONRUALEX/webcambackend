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
@RequestMapping("/mastertable")
public class MasterTableController  {
	
	@Autowired
	private MasterTableService service;
	
	/*@GetMapping(value = "/plataforma")
	@ResponseBody
	public ResponseEntity<List<MainTableDto>> getPlataforma() {
		List<MainTableDto> results = service.getPlataforma();
		return prepareResponse(results);
	}
	
	private ResponseEntity<List<MainTableDto>> prepareResponse(List<MainTableDto> results){
		return ResponseEntity.ok().body(results);
	}*/
	
}
