package com.suicidesquad.ChessSystem.controller;

import com.suicidesquad.ChessSystem.entity.Texture;
import com.suicidesquad.ChessSystem.service.TextureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/texture")
public class TextureController {

    private final TextureService textureService;

    @Autowired
    public TextureController(TextureService textureService) {
        this.textureService = textureService;
    }

    @GetMapping
    @CrossOrigin()
    public List<Texture> getTextures(){
        return textureService.getTextures();
    }

    @PostMapping("/add")
    @CrossOrigin()
    public void uploadTexture(@RequestParam("image")MultipartFile file) throws IOException {
        textureService.uploadTexture(file);
    }

    @GetMapping("/{filename}")
    @CrossOrigin()
    public ResponseEntity<?> downloadTexture(@PathVariable("filename") String filename) {
        byte[] imageData = textureService.downloadImage(filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @DeleteMapping("/{textureId}")
    @CrossOrigin()
    public void deleteTexture(@PathVariable("textureId") Long id){
        textureService.deleteTexture(id);
    }
}
