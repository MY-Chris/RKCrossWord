package com.acm.rossword.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WordObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public WordObject(String word, String wordMeaning) {
        this.word = word;
        this.wordMeaning = wordMeaning;
    }

    public String word;

    public String wordMeaning;


    public WordObject() {

    }
}
