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
    private final PositionRepository positionRepository;

    public GameService(GuestRepository guestRepository, GameRepository gameRepository, PositionRepository positionRepository) {
        this.guestRepository = guestRepository;
        this.gameRepository = gameRepository;
        this.positionRepository = positionRepository;
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
                Optional<Game> gameOptional = gameRepository.findFirstByUsersAndState(user, Game_state.active);
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
                        Position position = new Position("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 0, game);
                        positionRepository.save(position);
                    }
                    else if(opponent.getStatus() == User_status.active) {
                        user.setStatus(User_status.active);
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

    public Long getGame(Long userId) {
        Optional<Guest> userOptional = guestRepository.findById(userId);
        if(userOptional.isPresent())
        {
            Guest user = userOptional.get();
            Optional<Game> gameOptional = gameRepository.findFirstByUsersAndState(user, Game_state.active);
            if(gameOptional.isPresent()) {
                Game game = gameOptional.get();
                return game.getId();
            }
        }
        else {
            return null;
        }
        return null;
    }

    public String getColor(Long userId) {
        Optional<Guest> userOptional = guestRepository.findById(userId);
        if(userOptional.isPresent())
        {
            Guest user = userOptional.get();
            Optional<Game> gameOptional = gameRepository.findFirstByUsersAndState(user, Game_state.active);
            if(gameOptional.isPresent()) {
                Game game = gameOptional.get();
                if(Objects.equals(game.getWhiteId(), user.getGuestId())) {
                    return "White";
                }
                else {
                    return "Black";
                }
            }
        }
        else {
            return null;
        }
        return null;
    }

    public void savePosition(String fen, Long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if(gameOptional.isPresent()) {
            Game game = gameOptional.get();
            Position position = new Position(fen, 0, game);
            positionRepository.save(position);
        }
    }

    public void endGame(Long gameId, double result) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if(gameOptional.isPresent()) {
            Game game = gameOptional.get();
            if(result == 1) {
                game.setResult(End_game.white_won);
            }
            else if (result == 0.5) {
                game.setResult(End_game.draw);
            }
            else {
                game.setResult(End_game.black_won);
            }
            gameRepository.save(game);
            Long white = game.getWhiteId();
            Long black = game.getBlackId();

            Optional<Guest> optionalGuest = guestRepository.findById(white);
            Guest guest = optionalGuest.get();
            guest.setStatus(User_status.active);
            guestRepository.save(guest);

            optionalGuest = guestRepository.findById(black);
            guest = optionalGuest.get();
            guest.setStatus(User_status.active);
            guestRepository.save(guest);
        }
    }
}
