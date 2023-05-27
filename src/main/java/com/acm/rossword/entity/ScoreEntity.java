package com.acm.rossword.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ScoreEntity(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }

    public String userName;

    public int score;

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public ScoreEntity() {

    }
}
