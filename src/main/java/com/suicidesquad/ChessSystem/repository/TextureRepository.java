package com.suicidesquad.ChessSystem.repository;

import com.suicidesquad.ChessSystem.entity.Texture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TextureRepository  extends JpaRepository<Texture, Long> {

    @Query("SELECT t FROM Texture t WHERE t.name = ?1")
    Optional<Texture> findByName(String fileName);
}
