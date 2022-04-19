package com.example.olioharjoitusty;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Review {

    private int reviewScore;
    private String reviewString;

    public Review(){


    }
    public void publishReview(){
        HashMap <String , Object> map = new HashMap<>();
        map.put("Score", Integer.toString(reviewScore));
        map.put("Verbal", reviewString);
        FirebaseDatabase.getInstance("https://olio-riku-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Review").updateChildren(map);
    }

}
