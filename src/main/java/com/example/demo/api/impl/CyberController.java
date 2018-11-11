package com.example.demo.api.impl;

import com.example.demo.api.CyberApi;
import com.example.demo.service.CyberGamingService;
import com.example.demo.service.dto.CyberGamingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CyberController implements CyberApi {

    @Autowired
    CyberGamingService cyberGamingService;

    @Override
    public ResponseEntity<CyberGamingDTO> getById(@PathVariable("id") Integer id) {
        CyberGamingDTO cyberGamingDTO = cyberGamingService.getById(id);
        return ResponseEntity.ok(cyberGamingDTO);
    }

    @Override
    public ResponseEntity<CyberGamingDTO> create(@RequestBody CyberGamingDTO cyberGamingDTO) {
        CyberGamingDTO dto = cyberGamingService.create(cyberGamingDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<CyberGamingDTO> update(@RequestBody CyberGamingDTO cyberGamingDTO) {
        CyberGamingDTO dto = cyberGamingService.update(cyberGamingDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Integer id) {
        Boolean result = cyberGamingService.delete(id);
        Map<String, Boolean> resul = new HashMap<>();
        resul.put("Content", result);
        return ResponseEntity.ok(resul);
    }

    @Override
    public ResponseEntity<CyberGamingDTO> getByAccountId(@PathVariable("accountId") Integer accountId) {
        CyberGamingDTO cyberGamingDTO = cyberGamingService.getByAccountId(accountId);
        return ResponseEntity.ok(cyberGamingDTO);
    }

    @Override
    public ResponseEntity<List<CyberGamingDTO>> getAll() {
        List<CyberGamingDTO> cyberGamingDTOS = cyberGamingService.getAll();
        return ResponseEntity.ok(cyberGamingDTOS);
    }


}
