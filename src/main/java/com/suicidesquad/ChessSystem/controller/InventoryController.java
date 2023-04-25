package com.suicidesquad.ChessSystem.controller;

import com.suicidesquad.ChessSystem.entity.Inventory;
import com.suicidesquad.ChessSystem.entity.Texture;
import com.suicidesquad.ChessSystem.service.InventoryService;
import com.suicidesquad.ChessSystem.service.TextureService;
import com.suicidesquad.ChessSystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping(value= {"/add/{textureId}/{userId}", "/add/{textureId}"})
    @CrossOrigin()
    public void addTextureToUser(@PathVariable("textureId") Long textureId, @PathVariable("userId") Long userId){
        System.out.println(userId.toString());
        System.out.println(textureId.toString());
        inventoryService.addTextureToUser(textureId, userId);
    }

    @GetMapping("/{userId}")
    @CrossOrigin()
    public List<Inventory> getUserInventory(@PathVariable("userId") Long userId){
        return inventoryService.getUserInventory(userId);
    }
}
