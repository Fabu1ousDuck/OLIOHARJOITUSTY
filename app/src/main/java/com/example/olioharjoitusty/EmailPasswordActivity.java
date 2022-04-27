package com.example.olioharjoitusty;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.olioharjoitusty.ui.login.LoginViewModel;
import com.example.olioharjoitusty.ui.login.LoginViewModelFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class EmailPasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Movisio");
        LoginViewModel loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Button signin = (Button) findViewById(R.id.login);
        Button signup = (Button) findViewById(R.id.signup);

        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username = (EditText) findViewById(R.id.username);
                password = (EditText) findViewById(R.id.password);
                signIn(username.getText().toString(), password.getText().toString());
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = (EditText) findViewById(R.id.username);
                password = (EditText) findViewById(R.id.password);
                createAccount(username.getText().toString(), password.getText().toString());
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
            reload();
    }

    private void createAccount(String email, String password) {
        if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_!@#\\$%\\^&\\*]).{8,}$")){
            // [START create_user_with_email]
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(EmailPasswordActivity.this, "User creation succeeded.!",
                                        Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(EmailPasswordActivity.this, "Email not available",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
            // [END create_user_with_email]

        }else{
            updateUI(null);
            Toast.makeText(EmailPasswordActivity.this, "Creating account failed. Password must follow complexity requirements",
                    Toast.LENGTH_SHORT).show();
        }

    }
    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    private void reload() {
        mAuth.signOut();
        Toast.makeText(EmailPasswordActivity.this, "Signed out successfully",
                Toast.LENGTH_SHORT).show();
    }
    private void updateUI(FirebaseUser user) {
        if (user != null){
            //String path = user.getUid();

            //TÄSSÄ ON MITEN KIRJOITTAA FIREBASEEN
            //HashMap <String , Object> map = new HashMap<>();
            //map.put("Greeting", "HELLO");
            //map.put("Hotel", "Trivago");
            //FirebaseDatabase.getInstance("https://olio-riku-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child(path).child("message").updateChildren(map);

            String email = user.getEmail();
            String name = user.getDisplayName();
            String uid = user.getUid();
            Toast.makeText(EmailPasswordActivity.this, "Logging in succeeded!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();

            Account account = new Account(name, email, uid);
            intent.putExtra("acc", account);
            setResult(RESULT_OK, intent);
            finish();

        }
    }
}