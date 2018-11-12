package com.example.demo.api.impl;


import com.example.demo.api.RoomApi;
import com.example.demo.service.RoomService;
import com.example.demo.service.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoomController implements RoomApi {

    @Autowired
    RoomService roomService;

    @Override
    public ResponseEntity<List<RoomDTO>> getAll() {
        List<RoomDTO> roomDTOS = roomService.getAll();
        return ResponseEntity.ok(roomDTOS);
    }

    @Override
    public ResponseEntity<RoomDTO> create(@RequestBody RoomDTO roomDTO) {
        RoomDTO dto = roomService.create(roomDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<RoomDTO> update(@RequestBody RoomDTO roomDTO) {
        RoomDTO dto = roomService.update(roomDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<RoomDTO> getById(@PathVariable("id") Integer id) {
        RoomDTO roomDTO = roomService.getByID(id);
        return ResponseEntity.ok(roomDTO);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Integer id) {
        Boolean result = roomService.delete(id);
        Map<String, Boolean> resul = new HashMap<>();
        resul.put("Content", result);
        return ResponseEntity.ok(resul);
    }

    @Override
    public ResponseEntity<List<RoomDTO>> getByCyberId(Integer cyberId) {
        List<RoomDTO> roomDTOS = roomService.getByCyberId(cyberId);
        return ResponseEntity.ok(roomDTOS);
    }
}
