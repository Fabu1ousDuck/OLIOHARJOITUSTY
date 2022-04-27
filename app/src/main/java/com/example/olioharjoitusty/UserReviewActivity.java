package com.example.olioharjoitusty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class UserReviewActivity extends AppCompatActivity {
    ListView text;
    Context context;
    Account account;
    ArrayList <Review> movieObjects;
    ArrayList <String> favourites;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userreview);

        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("acc");
        text = (ListView) findViewById(R.id.favouriteListView);
        TextView name = (TextView) findViewById(R.id.usernName);
        name.setText(account.email1);
        context = this;

        favourites= new ArrayList<>();
        movieObjects = new ArrayList<>();

        final DatabaseReference ref = FirebaseDatabase.getInstance("https://olio-riku-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference().child("Review").child(account.uid2);
        // Attach a listener to read the data at our posts reference
        ref.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot datas: dataSnapshot.getChildren()){
                        Review userReview = datas.getValue(Review.class);
                        movieObjects.add(userReview);

                    }
                    onFinish(movieObjects);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
    public void onFinish(ArrayList <Review> movieObjects){
        movieObjects = orderbyReview(movieObjects);
        for (int i = 0; i < movieObjects.size(); i++){
            String x = movieObjects.get(i).getMovieName()+ "    "+movieObjects.get(i).getReviewScore();
            favourites.add(x);
        }
        ArrayAdapter linesAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, favourites);
        text.setAdapter(linesAdapter);
        ArrayList<Review> finalMovieObjects = movieObjects;
        text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String x = finalMovieObjects.get(i).getMovieName();
                String score = Float.toString(finalMovieObjects.get(i).getReviewScore());
                String date = finalMovieObjects.get(i).getDate();
                String reviewString = finalMovieObjects.get(i).getReviewString();
                Intent intent = new Intent(context, ReviewActivity.class);
                intent.putExtra("MovieName", x);
                intent.putExtra("Score", score);
                intent.putExtra("Date", date);
                intent.putExtra("Review", reviewString);
                intent.putExtra("userid", account.uid2);
                startActivityForResult(intent,3);
                System.out.println(x);
            }
        });
    }
    public ArrayList<Review> orderbyReview(ArrayList<Review> movieObjects){

        Collections.sort(movieObjects, new Sortbyreview());
        return movieObjects;
    }

}
class Sortbyreview implements Comparator<Review> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Review a, Review b)
    {
        return (int) (b.getReviewScore()-a.getReviewScore());
    }


}