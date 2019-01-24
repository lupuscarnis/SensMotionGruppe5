package com.example.nicolai.sensmotiongruppe5.Rute;

import com.example.nicolai.sensmotiongruppe5.Interface.IHighlight;

import java.util.ArrayList;

public class Quiz_Highlight implements IHighlight {
    private boolean end = false, start = false;
    private float x;
    private float y;
    private float radius;
    private Enum type;
    private String name;
    private boolean revealed;
    private String text = "hello Im the begining";
    private ArrayList<String> answers;

    public Quiz_Highlight(float x, float y, float radius, String name, String text) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.name = name;
        this.text = text;
    }

    public Quiz_Highlight(float x, float y, float radius, String name, boolean revealed, String text) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.name = name;
        this.revealed = revealed;
        this.text = text;
    }

    public Quiz_Highlight(float x, float y, float radius, Enum type, String name, boolean revealed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.type = type;
        this.name = name;
        this.revealed = revealed;
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

    @Override
    public Enum getType() {
        return type;
    }

    @Override
    public void setType(Enum type) {
        this.type = type;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    @Override

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
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

}
