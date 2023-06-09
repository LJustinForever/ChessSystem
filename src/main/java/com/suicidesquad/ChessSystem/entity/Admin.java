package com.suicidesquad.ChessSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import static com.suicidesquad.ChessSystem.utils.Utils.encodeString;

@Entity
@Table
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(nullable = false, unique = true)
    private String emailAddress;

    public Admin() {
    }

    public Admin(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public Admin(Long id, String username, String password, String emailAddress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public void encodePassword(){
        this.password = encodeString(this.password);
    }
}
