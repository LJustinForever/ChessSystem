package com.suicidesquad.ChessSystem.service;

import com.suicidesquad.ChessSystem.entity.*;
import com.suicidesquad.ChessSystem.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class GameService {

    private final GuestRepository guestRepository;
    private final GameRepository gameRepository;

    public GameService(GuestRepository guestRepository, GameRepository gameRepository) {
        this.guestRepository = guestRepository;
        this.gameRepository = gameRepository;
    }

    public Long createTemp() {
        Guests guest = new Guests();
        Guest savedGuest = guestRepository.save(guest);
        return savedGuest.getGuestId();
    }

    public User_status startUnrated(Long userId) {
        Optional<Guest> userOptional = guestRepository.findById(userId);
        if(userOptional.isPresent())
        {
            Guest user = userOptional.get();
            user.setStatus(User_status.searching_game);

            guestRepository.save(user);
            return user.getStatus();
        }
        else {
            return null;
        }
    }

    public User_status getStatus(Long userId) {
        Optional<Guest> userOptional = guestRepository.findById(userId);
        if(userOptional.isPresent())
        {
            Guest user = userOptional.get();
            if(user.getStatus() == User_status.searching_game){
                Optional<Guest> opponentOptional = guestRepository.findFirstByStatusAndIdIsNot(User_status.searching_game, userId);
                if(opponentOptional.isPresent()) {
                    Guest opponent = opponentOptional.get();
                    Random rn = new Random();
                    int answer = rn.nextInt(2) ;
                    Game game;
                    if (answer == 1) {
                        game = new Game(user, opponent);
                    }
                    else {
                        game = new Game(opponent, user);
                    }
                    gameRepository.save(game);
                    opponent.setStatus(User_status.waiting_for_confirmation);
                    guestRepository.save(opponent);
                    user.setStatus(User_status.waiting_for_confirmation);
                    guestRepository.save(user);
                }
            }
            else if (user.getStatus() == User_status.confirmed){
                Optional<Game> gameOptional = gameRepository.findFirstByBlackOrWhiteAndState(user, user, Game_state.active);
                if(gameOptional.isPresent()) {
                    Game game = gameOptional.get();
                    Guest opponent;
                    if(Objects.equals(game.getWhiteId(), user.getGuestId())) {
                        opponent = guestRepository.findById(game.getBlackId()).get();
                    }
                    else {
                        opponent = guestRepository.findById(game.getWhiteId()).get();
                    }
                    if(opponent.getStatus() == User_status.confirmed) {
                        user.setStatus(User_status.playing);
                        opponent.setStatus(User_status.playing);
                        guestRepository.save(user);
                        guestRepository.save(opponent);
                    }
                    else if(opponent.getStatus() == User_status.active) {
                        user.setStatus(User_status.searching_game);
                        guestRepository.save(user);
                        gameRepository.delete(game);
                    }
                }
            }
            return user.getStatus();
        }
        else {
            return null;
        }
    }

    public User_status confirm(Long userId) {
        Optional<Guest> userOptional = guestRepository.findById(userId);
        if(userOptional.isPresent())
        {
            Guest user = userOptional.get();
            user.setStatus(User_status.confirmed);

            guestRepository.save(user);
            return user.getStatus();
        }
        else {
            return null;
        }
    }

    public User_status decline(Long userId) {
        Optional<Guest> userOptional = guestRepository.findById(userId);
        if(userOptional.isPresent())
        {
            Guest user = userOptional.get();
            user.setStatus(User_status.active);

            guestRepository.save(user);
            return user.getStatus();
        }
        else {
            return null;
        }
    }
}
