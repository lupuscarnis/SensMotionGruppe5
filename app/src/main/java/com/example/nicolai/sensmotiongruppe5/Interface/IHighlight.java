package com.example.nicolai.sensmotiongruppe5.Interface;

 public interface IHighlight {


     Enum getType();

    void setType(Enum type);

    boolean isRevealed();

    void setRevealed(boolean revealed);

    String getName();

    void setName(String name);

    float getX();

    void setX(float x);

    float getY();

    void setY(float y);

    float getRadius();

    void setRadius(float radius);

     String getText();

     void setText(String text);

}
