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

public class Signup extends AppCompatActivity {
    FirebaseAuth auth;
    EditText emailetsu, nameetsu, addrsetsu, passetsu, phoneetsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


         emailetsu = findViewById(R.id.etemaillogin);
         nameetsu = findViewById(R.id.etpasswordlogin);
         addrsetsu = findViewById(R.id.etaddresssignup);
         passetsu = findViewById(R.id.etpasssignup);
         phoneetsu = findViewById(R.id.etphonesignup);


        Button signinbtn = findViewById(R.id.btnsignin);
        Button login2 = findViewById(R.id.btnlogin2);




        auth = FirebaseAuth.getInstance();



        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Signup.this, Login.class);
                startActivity(intent2);
            }
        });

       signinbtn.setOnClickListener(view -> {
         createUser();



       });



    }
    private void createUser(){
        String email = emailetsu.getText().toString();
        String password = passetsu.getText().toString();

        if(TextUtils.isEmpty(email)){
           emailetsu.setError("Email cannot be empty");
           emailetsu.requestFocus();

        }else if(TextUtils.isEmpty(password)){
            passetsu.setError("password cannot be empty");
            passetsu.requestFocus();


        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup.this, Login.class));
                    }else{
                        Toast.makeText(Signup.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }


    }
}