package com.suicidesquad.ChessSystem.controller;

import com.suicidesquad.ChessSystem.entity.User;
import com.suicidesquad.ChessSystem.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;

    private static final int ONE_DAY = 24*60*60;

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
    @ResponseBody
    @CrossOrigin()
    public Map<String, Long> loginUser(@RequestBody User user, HttpServletResponse response) throws NoSuchAlgorithmException {
        String jwtToken = userService.loginUser(user);

        Cookie cookie = new Cookie("id",jwtToken);
        cookie.setMaxAge(ONE_DAY);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        Map<String, Long> jsonResponse = new HashMap<>();
        jsonResponse.put("id", userService.getUser(user.getEmailAddress()).getId());
        return jsonResponse;
    }

    @PutMapping("/update/{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) int currency,
                           @RequestParam(required = false) String emailAddress){
        userService.updateUser(userId, username, password, currency, emailAddress);
    }
}
