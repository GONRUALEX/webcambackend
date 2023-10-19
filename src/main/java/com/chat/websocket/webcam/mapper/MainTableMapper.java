package com.chat.websocket.webcam.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.chat.websocket.webcam.bean.MainTableDto;
import com.chat.websocket.webcam.model.base.MasterTable;

@Mapper(componentModel = "spring")
public abstract class MainTableMapper {

public abstract MainTableDto toDto(Long id, String descripcio, Boolean valid);

public abstract MainTableDto toDto(MasterTable masterTable);

public abstract List<MainTableDto> toDto(List<? extends MasterTable> llista);

}