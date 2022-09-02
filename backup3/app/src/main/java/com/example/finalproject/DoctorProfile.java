package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorProfile extends AppCompatActivity {
    ImageView drprofileimg;
    TextView drnameprofiletxt, drphoneprofiletxt, drbioprofiletxt, drproprofiletxt, dremailprofiletxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);


        drprofileimg = findViewById(R.id.imgdoctorprofile);
        drbioprofiletxt = findViewById(R.id.txtdrbioprofile);
        drnameprofiletxt = findViewById(R.id.txtdrnameprofile);
        dremailprofiletxt = findViewById(R.id.txtdremailprofile);
        drphoneprofiletxt = findViewById(R.id.txtdrnumprofile);
        drproprofiletxt = findViewById(R.id.txtdrproprofile);

    }
}