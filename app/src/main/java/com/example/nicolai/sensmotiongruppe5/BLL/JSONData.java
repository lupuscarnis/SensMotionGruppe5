package com.example.nicolai.sensmotiongruppe5.BLL;

/**
 * Custom class for array (supports different data-types)
 */

public class JSONData {

    String startDate;
    // The three ints below are a little redundant (because we have startDate), but it makes it easier to work with the data.
    int year;
    int month;
    int day;
    double resting;
    double standing;
    double walking;
    double cycling;
    double exercise;
    double other;
    double nodata;
    double steps;

    // Setters
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) { this.month = month; }

    public void setDay(int day) {
        this.day = day;
    }

    public void setResting(double resting) {
        this.resting = resting;
    }

    public void setStanding(double standing) {
        this.standing = standing;
    }

    public void setWalking(double walking) {
        this.walking = walking;
    }

    public void setCycling(double cycling) {
        this.cycling = cycling;
    }

    public void setExercise(double exercise) {
        this.exercise = exercise;
    }

    public void setOtherd(double other) {
        this.other = other;
    }

    public void setNodata(double nodata) {
        this.nodata = nodata;
    }

    public void setSteps(double steps) {
        this.steps = steps;
    }

    // Getters
    public String getStartDate() {
        return startDate;
    }

    public int getYear() {
        return year;
    }

    public int geMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public double getResting() { return resting; }

    public double getStanding() {
        return standing;
    }

    public double getWalking() {
        return walking;
    }

    public double getCycling() {
        return cycling;
    }

    public double getExercise() {
        return exercise;
    }

    public double getOtherd() {
        return other;
    }

    public double getNodata() {
        return nodata;
    }

    public double getSteps() {
        return steps;
    }

}