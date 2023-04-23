package com.suicidesquad.ChessSystem.repository;

import com.suicidesquad.ChessSystem.entity.Inventory;
import com.suicidesquad.ChessSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("SELECT i FROM Inventory i join i.userId usr WHERE i.userId.id = ?1")
    List<Inventory> findAllByUserId(Long user);
}
