package com.example.olioharjoitusty;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Review {

    private float reviewScore;
    private String reviewString;

    public Review(){
        reviewScore = 5;
        reviewString = "";

    }
    public void setReviewScore(float score){
        reviewScore = score;
    }

    public void setReviewString(String reviewString) {
        this.reviewString = reviewString;
    }

    public void publishReview(String userid, String moviename){
        HashMap <String , Object> map = new HashMap<>();
        map.put("Score", Float.toString(reviewScore));
        map.put("Verbal", reviewString);
        FirebaseDatabase.getInstance("https://olio-riku-default-rtdb.europe-west1.firebasedatabase.app/").
                getReference().child("Review").child(userid).child(moviename).updateChildren(map);
    }

}