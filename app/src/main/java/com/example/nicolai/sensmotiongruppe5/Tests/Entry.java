package com.example.nicolai.sensmotiongruppe5.Tests;

public class Entry {
    String name, id, score;


    public Entry(String name, String score, String id) {
        this.name = name;
        this.score = score;
        this.id = id;
    }

    public Entry() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
