package com.suicidesquad.ChessSystem.service;

import com.suicidesquad.ChessSystem.entity.Guest;
import com.suicidesquad.ChessSystem.entity.Inventory;
import com.suicidesquad.ChessSystem.entity.Texture;
import com.suicidesquad.ChessSystem.entity.User;
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

    public Long createTemp(Guest guest) {
        Guest savedGuest = guestRepository.save(guest);
        return savedGuest.getGuestId();
    }
}
