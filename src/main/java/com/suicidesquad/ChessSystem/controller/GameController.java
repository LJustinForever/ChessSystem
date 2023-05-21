 package com.suicidesquad.ChessSystem.controller;


 import com.suicidesquad.ChessSystem.entity.User_status;
 import com.suicidesquad.ChessSystem.service.GameService;
 import org.springframework.web.bind.annotation.*;

 import java.io.IOException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
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

     @GetMapping("/startUnrated/{id}")
     @CrossOrigin()
     public User_status startUnrated(@PathVariable("id") Long id){
         return gameService.startUnrated(id);
     }

     @GetMapping("/getStatus/{id}")
     @CrossOrigin()
     public User_status getStatus(@PathVariable("id") Long id){
         return gameService.getStatus(id);
     }
 }
