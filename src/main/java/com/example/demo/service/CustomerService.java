package com.example.demo.service;

import com.example.demo.service.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO create(CustomerDTO customerDTO);

    CustomerDTO update(CustomerDTO customerDTO);

    Boolean delete(Integer id);

    List<CustomerDTO> getAll();
}
