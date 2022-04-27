package com.example.olioharjoitusty;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    private float reviewScore;
    private String movieName;
    private String reviewString;
    private String date;

    public Review(){
        movieName = "";
        reviewScore = 5;
        reviewString = "";
    }
    public void setReviewScore(float score){
        reviewScore = score;
    }
    public void setMovieName(String name){ movieName = name; }
    public void setDate(String date1){date = date1;}
    public void setReviewString(String reviewString1){reviewString = reviewString;}
    public float getReviewScore(){return reviewScore;}
    public String getMovieName() { return movieName; }
    public String getReviewString(){return reviewString;}
    public String getDate(){return date;}
}