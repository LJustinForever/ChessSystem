package com.suicidesquad.ChessSystem.repository;

import com.suicidesquad.ChessSystem.entity.Game;
import com.suicidesquad.ChessSystem.entity.Inventory;
import com.suicidesquad.ChessSystem.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    public List<Position> findAllByGameIdOrderById(Game game);
}
