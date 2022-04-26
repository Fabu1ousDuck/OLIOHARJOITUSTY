package com.example.olioharjoitusty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class ReviewActivity extends AppCompatActivity {
    Review review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getSupportActionBar().setTitle("Movisio");
        Intent intent = getIntent();
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        TextView moviename = (TextView) findViewById(R.id.ratingMovieName);
        String name = intent.getStringExtra("MovieName");

        String userid = intent.getStringExtra("userid");
        moviename.setText(name);
        Button publishbutton = (Button) findViewById(R.id.publishbutton);
        review = new Review();
        review.setMovieName(name);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                float rating =ratingBar.getRating();
                review.setReviewScore(rating);
            }
        });

        publishbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance("https://olio-riku-default-rtdb.europe-west1.firebasedatabase.app/").
                        getReference().child("Review").child(userid).child(review.getMovieName()).setValue(review);
            }
        }
        );


    }

}
