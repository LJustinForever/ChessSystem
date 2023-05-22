package com.suicidesquad.ChessSystem.repository;

import com.suicidesquad.ChessSystem.entity.Game;
import com.suicidesquad.ChessSystem.entity.Game_state;
import com.suicidesquad.ChessSystem.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findFirstByBlackIsOrWhiteIsAndStateIs(Guest black, Guest white, Game_state state);
}
