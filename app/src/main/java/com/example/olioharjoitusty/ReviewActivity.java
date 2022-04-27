package com.example.olioharjoitusty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class ReviewActivity extends AppCompatActivity {
    Review review;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getSupportActionBar().setTitle("Movisio");
        Intent intent = getIntent();

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        TextView moviename = (TextView) findViewById(R.id.ratingMovieName);
        EditText reviewString = (EditText) findViewById(R.id.editTextReview);
        EditText datestring = (EditText) findViewById(R.id.editTextDate);

        String name = intent.getStringExtra("MovieName");
        String userid = intent.getStringExtra("userid");
        reviewString.setText(" ");
        date = java.time.LocalDate.now().toString();
        datestring.setText(java.time.LocalDate.now().toString());
        if (intent.hasExtra("Score")){
            float score = Float.parseFloat(intent.getStringExtra("Score"));
            ratingBar.setRating(score);
        }
        if (intent.hasExtra("Date")){
            datestring.setText(intent.getStringExtra("Date"));
        }
        if (intent.hasExtra("Review")){
            reviewString.setText(intent.getStringExtra("Review"));
        }

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
                reviewString.getText();
                if(!date.equals(datestring.getText().toString())){
                    review.setDate(datestring.getText().toString());
                }else{review.setDate(date);}
                if(!reviewString.getText().toString().equals(" ")){
                    review.setReviewString(reviewString.getText().toString());
                }else{review.setReviewString("");}
                FirebaseDatabase.getInstance("https://olio-riku-default-rtdb.europe-west1.firebasedatabase.app/").
                        getReference().child("Review").child(userid).child(review.getMovieName()).setValue(review);
                finishReview();
            }
        }
        );


    }
    public void finishReview(){
        Toast.makeText(ReviewActivity.this, "Rating published", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

}
