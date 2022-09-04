package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Verification extends AppCompatActivity {
    Button verfy;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        verfy = findViewById(R.id.buttonver);
        mauth = FirebaseAuth.getInstance();


        verfy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user.isEmailVerified()){
                    startActivity(new Intent(Verification.this, Login.class));
                }else{
                    Toast.makeText(Verification.this, "Please Make Sure To  Verify Your Email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}