package com.example.demo.service;

import com.example.demo.service.dto.RoomDTO;

import java.util.List;

public interface RoomService {

    List<RoomDTO> getAll();

    RoomDTO create(RoomDTO roomDTO);

    RoomDTO update(RoomDTO roomDTO);

    RoomDTO getByID(Integer id);

    Boolean delete(Integer id);

    List<RoomDTO> getByCyberId(Integer cyberId);
}
