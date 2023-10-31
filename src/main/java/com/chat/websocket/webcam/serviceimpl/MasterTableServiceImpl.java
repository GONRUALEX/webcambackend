package com.chat.websocket.webcam.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chat.websocket.webcam.bean.MainTableDto;
import com.chat.websocket.webcam.mapper.MainTableMapper;
import com.chat.websocket.webcam.repository.tablevalue.LanguagesRepository;
import com.chat.websocket.webcam.repository.tablevalue.RoleRepository;
import com.chat.websocket.webcam.service.MasterTableService;

@Service
public class MasterTableServiceImpl implements MasterTableService{

	@Autowired
	private MainTableMapper mapper;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private LanguagesRepository languagesRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<MainTableDto> getRoles(){
		return mapper.toDto(roleRepository.findAllByOrderByDescriptionAsc());
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<MainTableDto> getLanguages(){
		return mapper.toDto(languagesRepository.findAllByOrderByDescriptionAsc());
	}
}