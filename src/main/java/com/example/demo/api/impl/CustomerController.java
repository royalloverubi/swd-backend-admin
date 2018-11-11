package com.example.demo.api.impl;

import com.example.demo.api.CustomerApi;
import com.example.demo.service.CustomerService;
import com.example.demo.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController implements CustomerApi {

    @Autowired
    CustomerService customerService;


    @Override
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO dto = customerService.update(customerDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO dto = customerService.create(customerDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Integer id) {
        Boolean result = customerService.delete(id);
        Map<String, Boolean> resul = new HashMap<>();
        resul.put("Content", result);
        return ResponseEntity.ok(resul);
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> getAll() {
        List<CustomerDTO> customerDTOS = customerService.getAll();
        return ResponseEntity.ok(customerDTOS);
    }
}
