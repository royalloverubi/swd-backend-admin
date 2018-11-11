package com.example.demo.service.impl;

import com.example.demo.persistent.entity.Customer;
import com.example.demo.persistent.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.dto.CustomerDTO;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer = customerRepository.save(customer);
        CustomerDTO dto = modelMapper.map(customer, CustomerDTO.class);
        return dto;
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer = customerRepository.saveAndFlush(customer);
        CustomerDTO dto = modelMapper.map(customer, CustomerDTO.class);
        return dto;
    }

    @Override
    public Boolean delete(Integer id) {
        Optional.ofNullable(customerRepository.findById(id)).orElseThrow(() -> new EntityNotFoundException());
        customerRepository.deleteByID(id);
        return true;
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<Customer> customers = customerRepository.getAll();
        if(ObjectUtils.isEmpty(customers)) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        List<CustomerDTO> dto = new ArrayList<>();
        for (Customer cus : customers ) {
            dto.add(modelMapper.map(cus, CustomerDTO.class));
        }
        return dto;
    }
}
