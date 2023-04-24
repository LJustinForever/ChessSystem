package com.suicidesquad.ChessSystem.service;

import com.suicidesquad.ChessSystem.entity.User;
import com.suicidesquad.ChessSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import static com.suicidesquad.ChessSystem.utils.Utils.generateJWT;
import static com.suicidesquad.ChessSystem.utils.Utils.encodeString;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
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

    public User getUser(Long id) {
        Optional<User> userById = userRepository.findById(id);
        if(userById.isPresent())
            return userById.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    public User getUser(String email) {
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        if(userByEmail.isPresent())
            return userByEmail.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    public boolean userExists(User user){
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmailAddress());
        return userByEmail.isPresent();
    }

    public boolean userExists(String email){
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        return userByEmail.isPresent();
    }

    public void addNewUser(User user) {
        if(userExists(user))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Address is taken");
        user.encodePassword();
        userRepository.save(user);
    }

    public String loginUser(User user) throws NoSuchAlgorithmException {
        if(!userExists(user))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found");
        user.encodePassword();
        if(!userRepository.findUserByEmailAndPassword(user.getEmailAddress(), user.getPassword()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect Password");
        return generateJWT(user.toString());
    }

    @Transactional
    public void updateUser(Long userId, String username, String password, Optional<Integer> currency, String emailAddress) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if(username != null && username.length() > 0 && !Objects.equals(user.getUsername(), username))
            user.setUsername(username);
        if(password != null && password.length() > 0 && !Objects.equals(user.getPassword(), encodeString(password)))
            user.setPassword(encodeString(password));
        //TODO: NELEISTI CURRENCY KEISTI USERIUI REIK CHECKo
        if(currency.isPresent() && user.getCurrency() != currency.get())
            user.setCurrency(currency.get());
        if(emailAddress != null && emailAddress.length() > 0 && !Objects.equals(user.getEmailAddress(), emailAddress)){
            if(userExists(emailAddress))
               throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is taken");
            user.setEmailAddress(emailAddress);
        }
    }
}
