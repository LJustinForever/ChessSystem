package com.suicidesquad.ChessSystem.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean userExists(User user){
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmailAddress());
        return userByEmail.isPresent();
    }

    public void addNewUser(User user) {
        if(userExists(user))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Address is taken");
        user.encodePassword();
        userRepository.save(user);
    }

    public void loginUser(User user) {
        if(!userExists(user))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found");
        user.encodePassword();
        if(!userRepository.findUserByEmailAndPassword(user.getEmailAddress(), user.getPassword()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect Password");
    }
}
