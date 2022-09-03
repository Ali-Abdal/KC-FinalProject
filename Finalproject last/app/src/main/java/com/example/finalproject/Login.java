package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText emailloginet, passwordloginet;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        Button loginbtn = findViewById(R.id.btnlogin);
        Button signupbtn = findViewById(R.id.btnsignup);
        emailloginet = findViewById(R.id.etemaillogin);
        passwordloginet = findViewById(R.id.etpasswordlogin);

        auth = FirebaseAuth.getInstance();




        loginbtn.setOnClickListener(view -> {
            LoginUser();

        });



        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

    }
    private void LoginUser(){
        String email = emailloginet.getText().toString();
        String password = passwordloginet.getText().toString();

        if(TextUtils.isEmpty(email)){
            emailloginet.setError("Email cannot be empty");
            emailloginet.requestFocus();

        }else if(TextUtils.isEmpty(password)){
            passwordloginet.setError("password cannot be empty");
            passwordloginet.requestFocus();
        }else{

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));

                    }else{
                        Toast.makeText(Login.this, "Log in Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }
            });
        }

    }
}