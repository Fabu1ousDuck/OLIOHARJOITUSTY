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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olioharjoitusty.ui.login.LoginActivity;
import com.firebase.ui.auth.data.model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout nDrawerlayout;
    private ActionBarDrawerToggle nToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nDrawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this, nDrawerlayout,R.string.open,R.string.close);
        nDrawerlayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView nav_view = (NavigationView) findViewById(R.id.navigationView);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.nav_login){
                    String s = getResources().getString(R.string.login);
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, EmailPasswordActivity.class);
                    startActivityForResult(intent,1);
                    return true;
                }
                if(id == R.id.nav_signout){
                    String s = getResources().getString(R.string.logout);
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,)

                }

                return false;
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
                TextView text = (TextView)findViewById(R.id.textView);
                Account myObject = (Account) data.getSerializableExtra("acc");
                text.setText(myObject.email1);

            }
        }

    }


}