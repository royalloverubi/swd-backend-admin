package com.example.demo.api.impl;

import com.example.demo.api.ImageApi;
import com.example.demo.service.ImageService;
import com.example.demo.service.dto.ImageDTO;
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
public class ImageController implements ImageApi {

    @Autowired
    ImageService imageService;

    @Override
    public ResponseEntity<List<ImageDTO>> getByCyberId(@RequestParam("cyberId") Integer cyberId) {
        List<ImageDTO> imageDTOS = imageService.getByCyberId(cyberId);
        return ResponseEntity.ok(imageDTOS);
    }

    @Override
    public ResponseEntity<ImageDTO> update(@RequestBody ImageDTO imageDTO) {
        ImageDTO dto = imageService.update(imageDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ImageDTO> create(@RequestBody ImageDTO imageDTO) {
        ImageDTO dto = imageService.create(imageDTO);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") Integer id) {
        Boolean result = imageService.delete(id);
        Map<String, Boolean> resul = new HashMap<>();
        resul.put("Content", result);
        return ResponseEntity.ok(resul);
    }
}
