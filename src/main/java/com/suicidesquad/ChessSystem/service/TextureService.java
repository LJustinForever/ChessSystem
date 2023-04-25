package com.suicidesquad.ChessSystem.service;

import com.suicidesquad.ChessSystem.entity.Texture;
import com.suicidesquad.ChessSystem.repository.TextureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.suicidesquad.ChessSystem.utils.Utils.compressImage;
import static com.suicidesquad.ChessSystem.utils.Utils.decompressImage;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class TextureService {

    private final TextureRepository textureRepository;

    @Autowired
    public TextureService(TextureRepository textureRepository) {
        this.textureRepository = textureRepository;
    }

    public boolean textureExists(String name){
        return textureRepository.findByName(name).isPresent();
    }

    public boolean textureExists(Long id){
        return textureRepository.findById(id).isPresent();
    }

    public void uploadTexture(Texture texture) throws IOException {
        Optional<Texture> texByName = textureRepository.findByName(texture.getName());
        if(texByName.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "File name exists");
        textureRepository.save(texture);
    }

    public byte[] downloadImage(String filename){
        Optional<Texture> dbImageData = textureRepository.findByName(filename);
        byte[] images=decompressImage(dbImageData.get().getImageData());
        return images;
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
