package com.suicidesquad.ChessSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.hash.Hashing;
import jakarta.persistence.*;
import static com.suicidesquad.ChessSystem.utils.Utils.encodeString;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique=true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int currency = 0;
    @Column(nullable = false)
    private LocalDate creationDate = LocalDate.now();
    @Column(nullable = false, unique=true)
    private String emailAddress;

    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private Set<Inventory> inventories = new HashSet<>();

    public User(){}

    public User(Long id, String username, String password, int currency, LocalDate creationDate, String emailAddress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.currency = currency;
        this.creationDate = creationDate;
        this.emailAddress = emailAddress;
    }

    public User(String username, String password, int currency, LocalDate creationDate, String emailAddress) {
        this.username = username;
        this.password = password;
        this.currency = currency;
        this.creationDate = creationDate;
        this.emailAddress = emailAddress;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", currency=" + currency +
                ", creationDate=" + creationDate +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public void encodePassword(){
        this.password = encodeString(this.password);
    }
}
