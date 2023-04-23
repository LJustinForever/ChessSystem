package com.suicidesquad.ChessSystem.service;

import com.suicidesquad.ChessSystem.entity.Texture;
import com.suicidesquad.ChessSystem.repository.TextureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class TextureService {

    private final TextureRepository textureRepository;

    @Autowired
    public TextureService(TextureRepository textureRepository) {
        this.textureRepository = textureRepository;
    }

    public boolean textureExists(Texture texture){
        return textureRepository.findByPath(texture.getPath()).isPresent();
    }

    public boolean textureExists(String path){
        return textureRepository.findByPath(path).isPresent();
    }

    public boolean textureExists(Long id){
        return textureRepository.findById(id).isPresent();
    }

    public void addNewTexture(Texture texture) throws IOException {
        //FIXME: IMPLEMENT ACTUAL UPLOAD XDDDDDDDDDDDDDDDDDDDDDD
        String[] parsed = texture.getPath().split("/");
        Path toPath = Path.of(System.getProperty("user.dir") + "/images/" + parsed[parsed.length - 1]);
        Path fromPath = Path.of(texture.getPath());
        if(textureExists(toPath.toString()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Texture path exists");
        Files.copy(fromPath, toPath, REPLACE_EXISTING);
        texture.setPath(toPath.toString());
        textureRepository.save(texture);
    }

    public void deleteTexture(Long id) {
        if(!textureExists(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Texture not found");
        textureRepository.deleteById(id);
    }

    public List<Texture> getTextures() {
        return textureRepository.findAll();
    }
}
