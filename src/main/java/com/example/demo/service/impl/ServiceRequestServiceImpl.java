package com.example.demo.service.impl;

import com.example.demo.persistent.entity.ServiceRequest;
import com.example.demo.persistent.repository.*;
import com.example.demo.service.ServiceRequestService;
import com.example.demo.service.dto.ServiceRequestDTO;
import com.example.demo.service.dto.ServiceRequestDetailDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final RoomRepository roomRepository;
    private final ConfigurationRepository configurationRepository;
    private final CyberGamingRepository cyberGamingRepository;
    private final CustomerRepository customerRepository;

    public ServiceRequestServiceImpl(ServiceRequestRepository serviceRequestRepository, RoomRepository roomRepository, ConfigurationRepository configurationRepository, CyberGamingRepository cyberGamingRepository, CustomerRepository customerRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.roomRepository = roomRepository;
        this.configurationRepository = configurationRepository;
        this.cyberGamingRepository = cyberGamingRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public ServiceRequestDetailDTO getById(Integer id) {
        ServiceRequest serviceRequest = serviceRequestRepository.getByID(id);
        if(ObjectUtils.isEmpty(serviceRequest)) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return updateDetail(serviceRequest);
    }

    @Override
    public ServiceRequestDTO create(ServiceRequestDTO serviceRequestDTO) {
        serviceRequestDTO = updatePrice(serviceRequestDTO);
        ModelMapper modelMapper = new ModelMapper();
        ServiceRequest serviceRequest = modelMapper.map(serviceRequestDTO, ServiceRequest.class);
        serviceRequest = serviceRequestRepository.save(serviceRequest);
        ServiceRequestDTO dto = modelMapper.map(serviceRequest, ServiceRequestDTO.class);
        return dto;
    }

    @Override
    public ServiceRequestDTO update(ServiceRequestDTO serviceRequestDTO) {
        serviceRequestDTO = updatePrice(serviceRequestDTO);
        ModelMapper modelMapper = new ModelMapper();
        ServiceRequest serviceRequest = modelMapper.map(serviceRequestDTO, ServiceRequest.class);
        serviceRequest = serviceRequestRepository.saveAndFlush(serviceRequest);
        ServiceRequestDTO dto = modelMapper.map(serviceRequest, ServiceRequestDTO.class);
        return dto;
    }

    @Override
    public Boolean delete(Integer id) {
        Optional.ofNullable(serviceRequestRepository.findById(id)).orElseThrow(() -> new EntityNotFoundException());
        serviceRequestRepository.deleteByID(id);
        return true;
    }

    @Override
    public List<ServiceRequestDetailDTO> getListNeedToAprove(Integer cyberId) {
        List<ServiceRequest> serviceRequests = serviceRequestRepository.getListNeedToAproving(cyberId);
        if(ObjectUtils.isEmpty(serviceRequests)) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        List<ServiceRequestDetailDTO> serviceRequestDTOS = new ArrayList<>();
        for (ServiceRequest sr: serviceRequests ) {
            serviceRequestDTOS.add(updateDetail(sr));
        }
        return serviceRequestDTOS;
    }

    @Override
    public Boolean approveARequest(Integer id) {
        Optional.ofNullable(serviceRequestRepository.findById(id)).orElseThrow(() -> new EntityNotFoundException());
        serviceRequestRepository.approveRequest(id);
        return true;
    }

    @Override
    public Boolean completeARequest(Integer id) {
        Optional.ofNullable(serviceRequestRepository.findById(id)).orElseThrow(() -> new EntityNotFoundException());
        serviceRequestRepository.completeRequest(id);
        return true;
    }

    @Override
    public List<ServiceRequestDetailDTO> getListNeedToDone(Integer cyberId) {
        List<ServiceRequest> serviceRequests = serviceRequestRepository.getListNeedToDone(cyberId);
        if(ObjectUtils.isEmpty(serviceRequests)) {
            return null;
        }
        List<ServiceRequestDetailDTO> serviceRequestDetailDTOS = new ArrayList<>();
        for (ServiceRequest sr: serviceRequests ) {
            serviceRequestDetailDTOS.add(updateDetail(sr));
        }
        return serviceRequestDetailDTOS;
    }

    private ServiceRequestDetailDTO updateDetail(ServiceRequest serviceRequest) {
        ModelMapper modelMapper = new ModelMapper();
        ServiceRequestDetailDTO dto = modelMapper.map(serviceRequest, ServiceRequestDetailDTO.class);
        dto.setUsername((String) customerRepository.getNameById(serviceRequest.getUserId()));
        dto.setRoomname((String) roomRepository.getNameById(serviceRequest.getRoomId()));
        dto.setCyberGamingName((String) cyberGamingRepository.getNameById(serviceRequest.getCyberGamingId()));
        dto.setConfigurationName((String) configurationRepository.getNameById(serviceRequest.getConfigurationId()));
        return dto;
    }


    private Date subtractDays(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.HOUR, -7);
        return cal.getTime();
    }

    private ServiceRequestDTO updatePrice(ServiceRequestDTO serviceRequestDTO) {
        Double roomPrice = (Double) roomRepository.getPriceById(serviceRequestDTO.getRoomId());
        Double configPrice = (Double) configurationRepository.getPriceId(serviceRequestDTO.getConfigurationId());
        if(ObjectUtils.isEmpty(roomPrice)) {
            roomPrice = 0D;
        }
        if(ObjectUtils.isEmpty(configPrice)) {
            configPrice = 0D;
        }
        if (ObjectUtils.isEmpty(serviceRequestDTO.getDuration())) {
            serviceRequestDTO.setDuration(0D);
        }
        Double totalPrice = (roomPrice + configPrice) * (serviceRequestDTO.getDuration() / 60);
        totalPrice = Math.round(totalPrice *10)/ 10.0;
        serviceRequestDTO.setTotalPrice(totalPrice);
        return serviceRequestDTO;
    }
}
