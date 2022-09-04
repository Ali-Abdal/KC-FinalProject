package com.example.finalproject.ui.Hospitals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.finalproject.R;
import com.example.finalproject.ui.Mapfragments.Map_FragmentJahra;
import com.example.finalproject.ui.Mapfragments.Map_fragmentHadi;

public class Hadihos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadihos);
        Fragment fragment = new Map_fragmentHadi();
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout, fragment).commit();
    }
}