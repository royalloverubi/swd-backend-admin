package com.example.demo.service;

import com.example.demo.service.dto.ServiceRequestDTO;
import com.example.demo.service.dto.ServiceRequestDetailDTO;

import java.util.List;

public interface ServiceRequestService {

    ServiceRequestDetailDTO getById(Integer id);

    ServiceRequestDTO create(ServiceRequestDTO serviceRequestDTO);

    ServiceRequestDTO update(ServiceRequestDTO serviceRequestDTO);

    Boolean delete(Integer id);

    List<ServiceRequestDetailDTO> getListNeedToAprove(Integer cyberId);

    Boolean approveARequest(Integer id);

    Boolean completeARequest(Integer id);

    List<ServiceRequestDetailDTO> getListNeedToDone(Integer cyberId);
}
