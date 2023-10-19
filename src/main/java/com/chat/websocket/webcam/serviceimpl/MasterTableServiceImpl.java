package com.chat.websocket.webcam.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.websocket.webcam.mapper.MainTableMapper;
import com.chat.websocket.webcam.service.MasterTableService;

@Service
public class MasterTableServiceImpl implements MasterTableService{

	@Autowired
	private MainTableMapper mapper;
	/*
	@Autowired
	private PlataformasRepository plataformaRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<MainTableDto> getPlataforma(){
		return mapper.toDto(plataformaRepository.findAllByOrderByDescriptionAsc());
	}*/
}