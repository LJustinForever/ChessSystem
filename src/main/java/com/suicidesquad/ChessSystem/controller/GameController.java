 package com.suicidesquad.ChessSystem.controller;

 import com.suicidesquad.ChessSystem.entity.Guest;
 import com.suicidesquad.ChessSystem.entity.Inventory;
 import com.suicidesquad.ChessSystem.entity.Texture;
 import com.suicidesquad.ChessSystem.entity.User_status;
 import com.suicidesquad.ChessSystem.service.GameService;
 import com.suicidesquad.ChessSystem.service.TextureService;
 import com.suicidesquad.ChessSystem.service.UserService;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.*;

 import java.io.IOException;
 import java.util.List;

 @RestController
 @RequestMapping("api/game")
 public class GameController {

     private final GameService gameService;

     public GameController(GameService gameService) {
         this.gameService = gameService;
     }

     @GetMapping("/createTemp")
     @CrossOrigin()
     public Long createTemp(){
         return gameService.createTemp();
     }

//     @PostMapping("/startUnrated/{id}")
//     @CrossOrigin()
//     public Long createTemp(@PathVariable("textureId") Long textureId){
//         return gameService.createTemp(guest);
//     }
 }
