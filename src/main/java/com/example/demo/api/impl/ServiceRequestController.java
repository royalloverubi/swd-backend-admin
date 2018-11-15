package com.example.demo.api.impl;

import com.example.demo.api.ServiceRequestApi;
import com.example.demo.service.ServiceRequestService;
import com.example.demo.service.dto.ServiceRequestDTO;
import com.example.demo.service.dto.ServiceRequestDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ServiceRequestController implements ServiceRequestApi {

    @Autowired
    ServiceRequestService serviceRequestService;

    @Override
    public ResponseEntity<ServiceRequestDetailDTO> getById(@PathVariable("id") Integer id) {
        ServiceRequestDetailDTO serviceRequestDTO = serviceRequestService.getById(id);
        return ResponseEntity.ok(serviceRequestDTO);
    }

    @Override
    public ResponseEntity<ServiceRequestDTO> update(@RequestBody ServiceRequestDTO serviceRequestDTO) {
        ServiceRequestDTO dto = serviceRequestService.update(serviceRequestDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ServiceRequestDTO> create(@RequestBody ServiceRequestDTO serviceRequestDTO) {
        ServiceRequestDTO dto = serviceRequestService.create(serviceRequestDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Integer id) {
        Boolean result = serviceRequestService.delete(id);
        Map<String, Boolean> resul = new HashMap<>();
        resul.put("Content", result);
        return ResponseEntity.ok(resul);
    }

    @Override
    public ResponseEntity<List<ServiceRequestDetailDTO>> getListNeedToAprove(@PathVariable("cyberId") Integer cyberId) {
        List<ServiceRequestDetailDTO> serviceRequestDTOS = serviceRequestService.getListNeedToAprove(cyberId);
        return ResponseEntity.ok(serviceRequestDTOS);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> approveRequest(@PathVariable("id") Integer id) {
        Boolean result = serviceRequestService.approveARequest(id);
        Map<String, Boolean> resul = new HashMap<>();
        resul.put("Content", result);
        return ResponseEntity.ok(resul);
    }

    @Override
    public ResponseEntity<List<ServiceRequestDetailDTO>> getListNeedToDone(@PathVariable("cyberId") Integer cyberId) {
        List<ServiceRequestDetailDTO> serviceRequestDTOS = serviceRequestService.getListNeedToDone(cyberId);
        return ResponseEntity.ok(serviceRequestDTOS);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> completeRequest(@PathVariable("id") Integer id) {
        Boolean result = serviceRequestService.completeARequest(id);
        Map<String, Boolean> resul = new HashMap<>();
        resul.put("Content", result);
        return ResponseEntity.ok(resul);
    }
}
