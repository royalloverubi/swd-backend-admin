package com.example.demo.service.impl;

import com.example.demo.persistent.entity.CyberGaming;
import com.example.demo.persistent.repository.CyberGamingRepository;
import com.example.demo.service.CyberGamingService;
import com.example.demo.service.dto.CyberGamingDTO;
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
public class CyberGamingServiceImpl implements CyberGamingService {

    private final CyberGamingRepository cyberGamingRepository;

    public CyberGamingServiceImpl(CyberGamingRepository cyberGamingRepository) {
        this.cyberGamingRepository = cyberGamingRepository;
    }

    @Override
    public CyberGamingDTO getByAccountId(Integer accountId) {
        CyberGaming cyberGaming = cyberGamingRepository.findByAccountId(accountId);
        if(cyberGaming == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        CyberGamingDTO cyberGamingDTO = modelMapper.map(cyberGaming, CyberGamingDTO.class);
        return cyberGamingDTO;
    }

    @Override
    public CyberGamingDTO getById(Integer id) {
        CyberGaming cyberGaming = cyberGamingRepository.findByID(id);
        if(ObjectUtils.isEmpty(cyberGaming)) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        CyberGamingDTO cyberGamingDTO = modelMapper.map(cyberGaming, CyberGamingDTO.class);
        return cyberGamingDTO;
    }

    @Override
    public CyberGamingDTO create(CyberGamingDTO cyberGamingDTO) {
        ModelMapper modelMapper = new ModelMapper();
        CyberGaming cyberGaming = modelMapper.map(cyberGamingDTO, CyberGaming.class);
        cyberGaming = cyberGamingRepository.save(cyberGaming);
        CyberGamingDTO dto = modelMapper.map(cyberGaming, CyberGamingDTO.class);
        return dto;
    }

    @Override
    public CyberGamingDTO update(CyberGamingDTO cyberGamingDTO) {
        Optional.ofNullable(cyberGamingRepository.findById(cyberGamingDTO.getId())).orElseThrow(() -> new EntityNotFoundException());
        ModelMapper modelMapper = new ModelMapper();
        CyberGaming cyberGaming = modelMapper.map(cyberGamingDTO, CyberGaming.class);
        cyberGaming = cyberGamingRepository.saveAndFlush(cyberGaming);
        CyberGamingDTO dto = modelMapper.map(cyberGaming, CyberGamingDTO.class);
        return dto;
    }

    @Override
    public Boolean delete(Integer id) {
        Optional.ofNullable(cyberGamingRepository.findById(id)).orElseThrow(() -> new EntityNotFoundException());
        cyberGamingRepository.deleteByID(id);
        return true;
    }

    @Override
    public List<CyberGamingDTO> getAll() {
        List<CyberGaming> cyberGamings = cyberGamingRepository.getAll();
        if(ObjectUtils.isEmpty(cyberGamings)) {
            return null;
        }
        List<CyberGamingDTO> cyberGamingDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (CyberGaming cyb : cyberGamings ) {
            cyberGamingDTOS.add(modelMapper.map(cyb, CyberGamingDTO.class));
        }
        return cyberGamingDTOS;
    }
}
