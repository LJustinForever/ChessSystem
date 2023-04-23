package com.suicidesquad.ChessSystem.service;

import com.suicidesquad.ChessSystem.entity.Inventory;
import com.suicidesquad.ChessSystem.entity.Texture;
import com.suicidesquad.ChessSystem.entity.User;
import com.suicidesquad.ChessSystem.repository.InventoryRepository;
import com.suicidesquad.ChessSystem.repository.TextureRepository;
import com.suicidesquad.ChessSystem.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final TextureRepository textureRepository;
    private final UserRepository userRepository;
    private final InventoryRepository inventoryRepository;

    public InventoryService(TextureRepository textureRepository, UserRepository userRepository, InventoryRepository inventoryRepository) {
        this.textureRepository = textureRepository;
        this.userRepository = userRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public void addTextureToUser(Long textureId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Texture> texture = textureRepository.findById(textureId);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        if(!texture.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Texture not found");
        Inventory inventory = new Inventory(user.get(), texture.get());
        inventoryRepository.save(inventory);
    }

    public List<Inventory> getUserInventory(Long userId) {
//        Optional<User> user = userRepository.findById(userId);
//        if(!user.isPresent())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//        List<Inventory> inventoryList = inventoryRepository.findAllByUserId(userId);
//        if(inventoryList.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No textures found");
//        return inventoryList;
        return inventoryRepository.findAll();
    }
}
