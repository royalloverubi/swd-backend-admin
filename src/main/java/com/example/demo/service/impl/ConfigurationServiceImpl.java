package com.example.demo.service.impl;

import com.example.demo.persistent.entity.Configuration;
import com.example.demo.persistent.repository.ConfigurationRepository;
import com.example.demo.service.ConfigurationService;
import com.example.demo.service.dto.ConfigurationDTO;
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
public class ConfigurationServiceImpl implements ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public ConfigurationServiceImpl(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public List<ConfigurationDTO> getByCyberId(Integer cyberId) {
        List<Configuration> configurations = configurationRepository.findAllByCyberGamingId(cyberId);
        if(ObjectUtils.isEmpty(configurations)) {
            return null;
        }
        List<ConfigurationDTO> configurationDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Configuration conf : configurations ) {
            configurationDTOS.add(modelMapper.map(conf, ConfigurationDTO.class));
        }
        return configurationDTOS;
    }

    @Override
    public ConfigurationDTO create(ConfigurationDTO configurationDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Configuration configuration = modelMapper.map(configurationDTO, Configuration.class);
        configuration = configurationRepository.save(configuration);
        ConfigurationDTO dto = modelMapper.map(configuration, ConfigurationDTO.class);
        return configurationDTO;
    }

    @Override
    public ConfigurationDTO update(ConfigurationDTO configurationDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Configuration configuration = modelMapper.map(configurationDTO, Configuration.class);
        configuration = configurationRepository.saveAndFlush(configuration);
        ConfigurationDTO dto = modelMapper.map(configuration, ConfigurationDTO.class);
        return configurationDTO;
    }

    @Override
    public Boolean delete(Integer id) {
        Optional.ofNullable(configurationRepository.findById(id)).orElseThrow(() -> new EntityNotFoundException());
        configurationRepository.deleteByID(id);
        return true;
    }

    @Override
    public ConfigurationDTO getById(Integer id) {
        Configuration configuration = configurationRepository.findByConId(id);
        if(ObjectUtils.isEmpty(configuration)) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        ConfigurationDTO configurationDTO = modelMapper.map(configuration, ConfigurationDTO.class);
        return configurationDTO;
    }
}
