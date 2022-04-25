package com.example.olioharjoitusty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.olioharjoitusty.ui.login.LoginActivity;
import com.firebase.ui.auth.data.model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


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
        getSupportActionBar().setTitle("Movisio");
        nDrawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this, nDrawerlayout,R.string.open,R.string.close);
        nDrawerlayout.addDrawerListener(nToggle);
        context = MainActivity.this;
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = new Intent(context, EmailPasswordActivity.class);
        startActivityForResult(intent,1);
        final NavigationView nav_view = (NavigationView) findViewById(R.id.navigationView);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.nav_login){
                    String s = getResources().getString(R.string.login);
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, EmailPasswordActivity.class);
                    startActivityForResult(intent,1);
                    return true;
                }

                return false;
            }

        });
        movies = new ArrayList<>();
        search = (EditText) findViewById(R.id.search);
        button = (Button) findViewById(R.id.button);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        movielist = new MovieList();
        for (int i = 0; i < movielist.list1.size(); i++) {
            movies.add(movielist.list1.get(i).name.toString());
        }
        text = (ListView) findViewById(R.id.listview);
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
        String input = search.getText().toString();
        String firstLetter = input.substring(0, 1);
        String remainingLetters = input.substring(1, input.length());

        // change the first letter to uppercase
        firstLetter = firstLetter.toUpperCase();

        // join the two substrings
        input = firstLetter + remainingLetters;

        ArrayList<String> find = new ArrayList<>();
        if (!input.isEmpty()){
            for(int i = 0; i < movies.size(); i++){

                System.out.println(movies.get(i));
                if(movielist.list1.get(i).name.contains(input)) {
                    find.add(movies.get(i));

                }
                if(movielist.list1.get(i).genre.contains(input)) {
                    find.add(movies.get(i));


                }
            }
            if (find.isEmpty()) {

                find.add("Movie not found");
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, find);
            text.setAdapter(adapter);

        }
    }


}