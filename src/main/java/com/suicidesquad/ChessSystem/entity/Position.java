package com.suicidesquad.ChessSystem.entity;

import jakarta.persistence.*;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String cypher;
    @Column(nullable = false)
    private int move;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game gameId;

    public Position() {

    }

    public Position(String cypher, int move, Game gameId) {
        this.cypher = cypher;
        this.move = move;
        this.gameId = gameId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCypher() {
        return cypher;
    }

    public void setCypher(String cypher) {
        this.cypher = cypher;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
}
