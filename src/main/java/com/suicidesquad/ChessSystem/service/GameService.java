package com.suicidesquad.ChessSystem.service;

import com.suicidesquad.ChessSystem.entity.*;
import com.suicidesquad.ChessSystem.repository.GuestRepository;
import com.suicidesquad.ChessSystem.repository.InventoryRepository;
import com.suicidesquad.ChessSystem.repository.TextureRepository;
import com.suicidesquad.ChessSystem.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GuestRepository guestRepository;

    public GameService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Long createTemp() {
        Guests guest = new Guests();
        Guest savedGuest = guestRepository.save(guest);
        return savedGuest.getGuestId();
    }
}
