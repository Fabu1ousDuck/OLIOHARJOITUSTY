package com.example.olioharjoitusty;

import java.io.Serializable;

public class Review implements Serializable {
    private float reviewScore;
    private String movieName;

    public Review(){
        movieName = "";
        reviewScore = 5;

    }
    public void setReviewScore(float score){
        reviewScore = score;
    }

    public void setMovieName(String name){ movieName = name; }
    public float getReviewScore(){return reviewScore;}

    public String getMovieName() { return movieName; }
}