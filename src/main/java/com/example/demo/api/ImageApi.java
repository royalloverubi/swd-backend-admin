package com.example.demo.api;

import com.example.demo.service.dto.ImageDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Component
@RequestMapping("/images")
public interface ImageApi {

    @ApiOperation(tags = {"Image",}, notes = "", value = "Get Image By Cyber Id")
    @GetMapping("/getByCyberId")
    ResponseEntity<List<ImageDTO>> getByCyberId(@RequestParam("cyberId") Integer cyberId);

    @ApiOperation(tags = {"Image",}, notes = "", value = "Update Image")
    @PutMapping("")
    ResponseEntity<ImageDTO> update(@RequestBody ImageDTO imageDTO);

    @ApiOperation(tags = {"Image",}, notes = "", value = "Create Image")
    @PostMapping("")
    ResponseEntity<ImageDTO> create(@RequestBody ImageDTO imageDTO);

    @ApiOperation(tags = {"Image",}, notes = "", value = "Delete Image")
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Integer id);
}
