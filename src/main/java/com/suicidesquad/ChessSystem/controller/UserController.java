package com.suicidesquad.ChessSystem.controller;

import com.suicidesquad.ChessSystem.entity.User;
import com.suicidesquad.ChessSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;

    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Long id){
        return userService.getUser(id);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user){
        userService.addNewUser(user);
    }



    @PostMapping("/login")
    public void loginUser(@RequestBody User user){
        userService.loginUser(user);
    }
}
