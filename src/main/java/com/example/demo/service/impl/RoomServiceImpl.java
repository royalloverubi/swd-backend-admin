package com.example.demo.service.impl;

import com.example.demo.persistent.entity.Room;
import com.example.demo.persistent.repository.RoomRepository;
import com.example.demo.service.RoomService;
import com.example.demo.service.dto.RoomDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> getAll() {
        List<Room> rooms = roomRepository.getALL();
        if(ObjectUtils.isEmpty(rooms)) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room roo : rooms ) {
            roomDTOS.add(modelMapper.map(roo, RoomDTO.class));
        }
        return roomDTOS;
    }

    @Override
    public RoomDTO create(RoomDTO roomDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Room room = modelMapper.map(roomDTO, Room.class);
        room = roomRepository.save(room);
        RoomDTO dto = modelMapper.map(room, RoomDTO.class);
        return dto;
    }

    @Override
    public RoomDTO update(RoomDTO roomDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Room room = modelMapper.map(roomDTO, Room.class);
        room = roomRepository.saveAndFlush(room);
        RoomDTO dto = modelMapper.map(room, RoomDTO.class);
        return dto;
    }

    @Override
    public RoomDTO getByID(Integer id) {
        Room room = roomRepository.findByID(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(room, RoomDTO.class);
    }

    @Override
    public Boolean delete(Integer id) {
        Optional.ofNullable(roomRepository.findById(id)).orElseThrow(() -> new EntityNotFoundException());
        roomRepository.deleteByID(id);
        return true;
    }

    @Override
    public List<RoomDTO> getByCyberId(Integer cyberId) {
        List<Room> rooms = roomRepository.getByCyberGamingId(cyberId);
        if(ObjectUtils.isEmpty(rooms)) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room roo : rooms ) {
            roomDTOS.add(modelMapper.map(roo, RoomDTO.class));
        }
        return roomDTOS;
    }
}
