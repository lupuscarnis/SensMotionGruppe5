package com.example.nicolai.sensmotiongruppe5.Rute;


import com.example.nicolai.sensmotiongruppe5.Interface.IHighlight;

public class Text_Highlight implements IHighlight {
    private boolean end = false, start = false;
    private float x;
    private float y;
    private float radius;
    private Enum type;
    private String text = "hello Im the begining";

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean isEnd() {
        return end;
    }

    @Override
    public void setEnd(boolean end) {
        this.end = end;
    }

    @Override
    public boolean isStart() {
        return start;
    }

    @Override
    public void setStart(boolean start) {

        this.start = start;

    }

    public Text_Highlight(float x, float y, float radius, Enum type, String name, boolean revealed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.type = type;
        this.name = name;
        this.revealed = revealed;
    }

    @Override
    public Enum getType() {
        return type;
    }

    @Override
    public void setType(Enum type) {
        this.type = type;
    }

    private String name;
    private boolean revealed;

    public Text_Highlight(float x, float y, float radius, String name, boolean revealed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.name = name;
        this.revealed = revealed;
    }

    public Text_Highlight(float x, float y, float radius, String name) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.name = name;
    }

    @Override
    public boolean isRevealed() {
        return revealed;
    }

    @Override
    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Text_Highlight() {
    }

    public Text_Highlight(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public void setRadius(float radius) {
        this.radius = radius;
    }

}
