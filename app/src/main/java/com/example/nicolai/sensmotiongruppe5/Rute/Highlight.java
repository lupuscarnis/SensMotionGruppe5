package com.example.nicolai.sensmotiongruppe5.Rute;

public class Highlight {

    private float x;
    private float y;
    private float radius;

    private String name;
    private boolean revealed;

    public Highlight(float x, float y, float radius, String name, boolean revealed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.name = name;
        this.revealed = revealed;
    }

    public Highlight(float x, float y, float radius, String name) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.name = name;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Highlight() {
    }

    public Highlight(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

}
