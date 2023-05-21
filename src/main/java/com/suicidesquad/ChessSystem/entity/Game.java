package com.suicidesquad.ChessSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.hash.Hashing;
import jakarta.persistence.*;
import static com.suicidesquad.ChessSystem.utils.Utils.encodeString;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Game_state state;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true)
    private End_game result;

    @ManyToOne
    @JoinColumn(name = "white", nullable = false)
    private User white;
    @ManyToOne
    @JoinColumn(name = "black", nullable = false)
    private User black;

    public Game(User white, User black) {
        this.state = Game_state.active;
        this.white = white;
        this.black = black;
    }
}
