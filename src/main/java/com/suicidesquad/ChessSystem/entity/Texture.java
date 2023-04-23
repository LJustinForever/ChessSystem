package com.suicidesquad.ChessSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Texture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String path;

    @OneToMany(mappedBy = "textureId", cascade = CascadeType.ALL)
    @JsonIgnore
    private final Set<Inventory> inventories = new HashSet<>();


    public Texture() {
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public Texture(Long id, String path) {
        this.id = id;
        this.path = path;
    }

    public Texture(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
