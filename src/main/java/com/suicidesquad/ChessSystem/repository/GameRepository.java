package com.suicidesquad.ChessSystem.repository;

import com.suicidesquad.ChessSystem.entity.Game;
import com.suicidesquad.ChessSystem.entity.Game_state;
import com.suicidesquad.ChessSystem.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g WHERE (g.black = :user OR g.white = :user) AND g.state = :state")
    Optional<Game> findFirstByUsersAndState(@Param("user") Guest user, @Param("state") Game_state state);
}
