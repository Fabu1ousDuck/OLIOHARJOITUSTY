package com.example.olioharjoitusty;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Review {
    private DatabaseReference mDatabase;

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
    public void getReview(String userId, String moviename){
        mDatabase = FirebaseDatabase.getInstance("https://olio-riku-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        mDatabase.child("Review").child(userId).child(moviename).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    task.getResult().toString();
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });

    }

}