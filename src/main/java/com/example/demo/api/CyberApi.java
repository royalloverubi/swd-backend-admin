package com.example.demo.api;


import com.example.demo.service.dto.CyberGamingDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Component
@RequestMapping("/cyber")
public interface CyberApi {

    @ApiOperation(tags = {"Cyber",}, notes = "", value = "Get Cyber By Id")
    @GetMapping("/{id}")
    ResponseEntity<CyberGamingDTO> getById(@PathVariable("id") Integer id);

    @ApiOperation(tags = {"Cyber",}, notes = "", value = "Create new Cyber")
    @PostMapping("")
    ResponseEntity<CyberGamingDTO> create(@RequestBody CyberGamingDTO cyberGamingDTO);

    @ApiOperation(tags = {"Cyber",}, notes = "", value = "Update Cyber")
    @PutMapping("")
    ResponseEntity<CyberGamingDTO> update(@RequestBody CyberGamingDTO cyberGamingDTO);

    @ApiOperation(tags = {"Cyber",}, notes = "", value = "Delete Cyber")
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Integer id);

    @ApiOperation(tags = {"Cyber",}, notes = "", value = "Get Cyber By AccountId")
    @GetMapping("/getByAccountId/{accountId}")
    ResponseEntity<CyberGamingDTO> getByAccountId(@PathVariable("accountId") Integer accountId);

    @ApiOperation(tags = {"Cyber",}, notes = "", value = "Get All Cyber")
    @GetMapping("")
    ResponseEntity<List<CyberGamingDTO>> getAll();
}
