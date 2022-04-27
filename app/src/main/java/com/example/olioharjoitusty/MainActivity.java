package com.example.olioharjoitusty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout nDrawerlayout;
    private ActionBarDrawerToggle nToggle;

    ListView text;
    Context context = null;
    ArrayList<String> movies;
    EditText search;
    Button button;
    MovieList movielist;
    Account account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Movisio");
        nDrawerlayout = findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this, nDrawerlayout,R.string.open,R.string.close);
        nDrawerlayout.addDrawerListener(nToggle);
        context = MainActivity.this;
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = new Intent(context, EmailPasswordActivity.class);
        startActivityForResult(intent,1);

        final NavigationView nav_view = findViewById(R.id.navigationView);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.nav_signout){
                    String s = getResources().getString(R.string.login);
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, EmailPasswordActivity.class);
                    startActivityForResult(intent,1);
                    return true;
                }else if (id == R.id.nav_favourites){
                    Intent intent = new Intent(context, UserReviewActivity.class);
                    intent.putExtra("acc", account);
                    startActivity(intent);
                    return true;
                }else if (id == R.id.movie_dashoboard) {
                    Intent intent = new Intent(context, MovieDashboardActivity.class);
                    intent.putExtra("acc", account);
                    intent.putExtra("list", (Serializable) movielist);
                    startActivity(intent);
                    return true;
                }
                return false;
            }

        });
        movies = new ArrayList<>();
        search = findViewById(R.id.search);
        button = findViewById(R.id.button);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        movielist = new MovieList();
        for (int i = 0; i < movielist.list1.size(); i++) {
            movies.add(movielist.list1.get(i).getMovName());
        }
        text = findViewById(R.id.listview);
        ArrayAdapter linesAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, movies);
        text.setAdapter(linesAdapter);
        text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String x = text.getItemAtPosition(i).toString();
                Intent intent = new Intent(context, ReviewActivity.class);
                intent.putExtra("MovieName", x);
                intent.putExtra("userid", account.uid2);
                startActivityForResult(intent,2);
                System.out.println(x);
            }
        });

        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (nToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                account = (Account) data.getSerializableExtra("acc");
            }
        }

    }

    //search movies by name, genre or actor
    public void searchMovie(View v) {
        String input = "";
        ArrayList<String> find = new ArrayList<>();
        if(!search.getText().toString().isEmpty()) {
            input = search.getText().toString();
            String firstLetter = input.substring(0, 1);
            String remainingLetters = input.substring(1);
            // change the first letter to uppercase
            firstLetter = firstLetter.toUpperCase();

            // join the two substrings
            input = firstLetter + remainingLetters;

            for (int i = 0; i < movies.size(); i++) {
                System.out.println(movies.get(i));
                //search movie by its title
                if (movielist.list1.get(i).getMovName().contains(input)) {
                    find.add(movies.get(i));
                }
                //search movie by its genre
                if (movielist.list1.get(i).getGenre().contains(input)) {
                    find.add(movies.get(i));
                }

                for (Actor a : movielist.list1.get(i).getActorArraylist()) {
                    String fullName = a.getFirstname() + " " + a.getLastname();

                        //search movie by actor's full name
                    if (fullName.equalsIgnoreCase(input)) {
                        find.add(movies.get(i));

                        //search movie by actor's first name
                    } else if (a.getFirstname().equalsIgnoreCase(input)) {
                        find.add(movies.get(i));
                        //search movie by actor's last name
                    } else if (a.getLastname().equalsIgnoreCase(input)) {
                        find.add(movies.get(i));
                    }
                }
            }
            if (find.isEmpty()) {
                find.add("Movie not found");
            }
        }else{
            find = movies;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, find);
        text.setAdapter(adapter);
    }
}