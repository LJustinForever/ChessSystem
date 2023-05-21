package com.suicidesquad.ChessSystem.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Guest {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true)
    private User_status result;

    public Long getGuestId() {
        return id;
    }

    // Getters and Setters
    // Constructors
    // Other methods
}
