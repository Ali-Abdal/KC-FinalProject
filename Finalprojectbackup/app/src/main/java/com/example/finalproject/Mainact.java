package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Mainact extends AppCompatActivity {
    FirebaseAuth auth;
    Button logoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        logoutbtn = findViewById(R.id.btnlogout);

        logoutbtn.setOnClickListener(view -> {
            auth.signOut();
            startActivity(new Intent(Mainact.this, Login.class));

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(Mainact.this, Login.class));

        }
    }
}