package com.suicidesquad.ChessSystem.user;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    public List<User> getUsers() {
        return List.of( new User(1L, "Username", "Password", 69, LocalDate.now(), "deez@nuts.org"),
                new User(2L, "Username2", "Password2", 692, LocalDate.now(), "deez2@nuts.org"));
    }
}
