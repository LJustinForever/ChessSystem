package com.suicidesquad.ChessSystem.controller;

import com.suicidesquad.ChessSystem.entity.Texture;
import com.suicidesquad.ChessSystem.service.TextureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Texture> getTextures(){
        return textureService.getTextures();
    }

    @PostMapping("/add")
    public void addTexture(@RequestBody Texture texture) throws IOException {
        textureService.addNewTexture(texture);
    }

    @DeleteMapping("/{textureId}")
    public void deleteTexture(@PathVariable("textureId") Long id){
        textureService.deleteTexture(id);
    }
}
