package com.example.demo.api.impl;

import com.example.demo.api.ConfigurationApi;
import com.example.demo.service.ConfigurationService;
import com.example.demo.service.dto.ConfigurationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConfigurationController implements ConfigurationApi {

    @Autowired
    ConfigurationService configurationService;

    @Override
    public ResponseEntity<List<ConfigurationDTO>> getByCyberId(@PathVariable("cyberId") Integer cyberId) {
        List<ConfigurationDTO> configurationDTOS = configurationService.getByCyberId(cyberId);
        return ResponseEntity.ok(configurationDTOS);
    }

    @Override
    public ResponseEntity<ConfigurationDTO> create(@RequestBody ConfigurationDTO configurationDTO) {
        ConfigurationDTO dto = configurationService.create(configurationDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ConfigurationDTO> update(@RequestBody ConfigurationDTO configurationDTO) {
        ConfigurationDTO dto = configurationService.create(configurationDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Integer id) {
        Boolean result = configurationService.delete(id);
        Map<String, Boolean> resul = new HashMap<>();
        resul.put("Content", result);
        return ResponseEntity.ok(resul);
    }

    @Override
    public ResponseEntity<ConfigurationDTO> getById(@PathVariable("Id") Integer Id) {
        ConfigurationDTO configurationDTO = configurationService.getById(Id);
        return ResponseEntity.ok(configurationDTO);
    }


}
