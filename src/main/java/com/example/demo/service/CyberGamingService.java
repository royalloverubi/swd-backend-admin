package com.example.demo.service;

import com.example.demo.service.dto.CyberGamingDTO;

import java.util.List;

public interface CyberGamingService {

    CyberGamingDTO getByAccountId(Integer accountId);

    CyberGamingDTO getById(Integer id);

    CyberGamingDTO create(CyberGamingDTO cyberGamingDTO);

    CyberGamingDTO update(CyberGamingDTO cyberGamingDTO);

    Boolean delete(Integer id);

    List<CyberGamingDTO> getAll();

}
