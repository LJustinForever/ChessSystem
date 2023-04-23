package com.suicidesquad.ChessSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class Inventory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
    @ManyToOne
    @JoinColumn(name = "texture_id", nullable = false)
    private Texture textureId;

    public Inventory() {
    }

    public Inventory(User user, Texture texture) {
        this.userId = user;
        this.textureId = texture;
    }

    public Inventory(Long id, User user, Texture texture) {
        this.id = id;
        this.userId = user;
        this.textureId = texture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Texture getTexture() {
        return textureId;
    }

    public void setTexture(Texture texture) {
        this.textureId = texture;
    }
}
