package com.suicidesquad.ChessSystem.repository;

import com.suicidesquad.ChessSystem.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("SELECT a FROM Admin a WHERE a.emailAddress = ?1")
    Optional<Admin> findAdminByEmail(String emailAddress);

    @Query("SELECT u FROM Admin u WHERE u.emailAddress = ?1 AND u.password = ?2")
    Optional<Admin> findAdminByEmailAndPassword(String emailAddress, String password);
}
