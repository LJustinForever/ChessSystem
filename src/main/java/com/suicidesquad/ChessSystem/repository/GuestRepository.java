package com.suicidesquad.ChessSystem.repository;

import com.suicidesquad.ChessSystem.entity.Guest;
import com.suicidesquad.ChessSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

}
