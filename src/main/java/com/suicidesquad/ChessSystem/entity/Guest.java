package com.suicidesquad.ChessSystem.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Guest {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private String socket;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true)
    private User_status status;

    public Guest() {
        this.status = User_status.active;
    }

    public Long getGuestId() {
        return id;
    }

    public User_status getGuestStatus() {
        return status;
    }

    public void setStatus(User_status status) {
        this.status = status;
    }

    public User_status getStatus() {
        return status;
    }

    // Getters and Setters
    // Constructors
    // Other methods
}
