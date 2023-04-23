package com.suicidesquad.ChessSystem.user;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int currency = 0;
    @Column(nullable = false)
    private LocalDate creationDate;
    @Column(nullable = false)
    private String emailAddress;

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
                ", password='" + password + '\'' +
                ", currency=" + currency +
                ", creationDate=" + creationDate +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
