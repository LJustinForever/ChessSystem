 package com.suicidesquad.ChessSystem.controller;


 import com.suicidesquad.ChessSystem.entity.User_status;
 import com.suicidesquad.ChessSystem.service.GameService;
 import org.springframework.messaging.handler.annotation.DestinationVariable;
 import org.springframework.messaging.handler.annotation.MessageMapping;
 import org.springframework.messaging.handler.annotation.SendTo;
 import org.springframework.messaging.simp.annotation.SendToUser;
 import org.springframework.web.bind.annotation.*;

 import java.io.IOException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;

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

     @GetMapping("/confirm/{id}")
     @CrossOrigin()
     public User_status confirm(@PathVariable("id") Long id){
         return gameService.confirm(id);
     }

     @GetMapping("/decline/{id}")
     @CrossOrigin()
     public User_status decline(@PathVariable("id") Long id){
         return gameService.decline(id);
     }

     @GetMapping("/getGame/{id}")
     @CrossOrigin()
     public Long getGame(@PathVariable("id") Long id){
         return gameService.getGame(id);
     }

     @GetMapping("/getColor/{id}")
     @CrossOrigin()
     public String getColor(@PathVariable("id") Long id){
         return gameService.getColor(id);
     }

     @MessageMapping("/makeMove/{gameId}")
     @SendTo("/getMove/game/{gameId}")
     @CrossOrigin()
     public String makeMove(@DestinationVariable("gameId") Long gameId, @RequestBody Map<String, Object> payload){
         String fen = String.valueOf(payload);
         gameService.savePosition(fen, gameId);
         return fen;
     }
 }
