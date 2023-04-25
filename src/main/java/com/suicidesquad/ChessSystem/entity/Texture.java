package com.suicidesquad.ChessSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@Builder
public class Texture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;

    private String type;

    @OneToMany(mappedBy = "textureId", cascade = CascadeType.ALL)
    @JsonIgnore
    private final Set<Inventory> inventories = new HashSet<>();
    @Lob
    @Column(length = 2097152)
    private String imageData;


    public Texture() {
    }

    public Texture(String name, String type, String imageData) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }

    public Texture(Long id, String name, String type, String imageData) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }


    public Set<Inventory> getInventories() {
        return inventories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}
