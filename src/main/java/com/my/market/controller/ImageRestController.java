package com.my.market.controller;

import com.my.market.model.Image;
import com.my.market.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class ImageRestController {
    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImage(@PathVariable(name = "id") int id){
        Image image = imageRepository.findById(id).orElseThrow();
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
