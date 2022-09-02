package com.example.finalproject.ui.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.finalproject.DocsAdapter;
import com.example.finalproject.DoctorProfile;
import com.example.finalproject.Doctors;
//import com.example.finalproject.DoctorsAdapter;
import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentHomeBinding;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    DatabaseReference database;
    ViewPager2 viewPager2;
    ArrayList<Doctors> doctorsArrayList = new ArrayList<>();

    CardView johndoc, lucasdoc, ameliadoc,andrewdoc,michealdoc,williamdoc;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View v = inflater.inflate(R.layout.fragment_home, container, false);

         johndoc = v.findViewById(R.id.doc_john);
         lucasdoc = v.findViewById(R.id.doc_lucas);
         ameliadoc = v.findViewById(R.id.doc_amelia);
         andrewdoc = v.findViewById(R.id.doc_andrew);
         michealdoc = v.findViewById(R.id.doc_michael);
         williamdoc = v.findViewById(R.id.doc_william);


        Doctors doc1 = new Doctors("John", "doctor.john@gmail.com", "Anesthesiologists",
                "An Anesthesiologists, graudated from University Of Glasgow. Has more than 5 years of expearince. Won the best doctor prize in Anesthesiology",
                R.drawable.john,37749949);

        Doctors doc2 = new Doctors("Lucas", "doctor.Lucas@gmail.com", "Cardiologists",
                "An Cardiologists, graudated from University Of Manchester. Has more than 9 years of expearince. Won the best doctor prize in Cardiology",
                R.drawable.lucas,19280982);

        Doctors doc3 = new Doctors("Amelia", "doctor.Amelia@gmail.com", "Dermatologists",
                "An Dermatologists, graudated from Universite de Paris. Has more than 6 years of expearince. Won the best doctor prize in Dermatology",
                R.drawable.amelia,99671467);

        Doctors doc4 = new Doctors("Andrew", "doctor.Andrew@gmail.com", "Neurologist",
                "An Neurologist, graudated from Harvard University. Has more than 10 years of expearince. Won the best doctor prize in Neurology",
                R.drawable.andrew,19839909);

        Doctors doc5 = new Doctors("Micheal", "doctor.Micheal@gmail.com", "Psychiatrists",
                "An Psychiatrists, graudated from Stanford University. Has more than 12 years of expearince. Won the best doctor prize in Psychiatry",
                R.drawable.michael,99882299);

        Doctors doc6 = new Doctors("William", "doctor.William@gmail.com", "Pathologists",
                "An Pathologists, graudated from University of Sheffield. Has more than 8 years of expearince. Won the best doctor prize in Pathology",
                R.drawable.william,18819292);

            doctorsArrayList.add(doc1);
            doctorsArrayList.add(doc2);
            doctorsArrayList.add(doc3);
            doctorsArrayList.add(doc4);
            doctorsArrayList.add(doc5);
            doctorsArrayList.add(doc6);

            DocsAdapter docsAdapter = new DocsAdapter(this,0,doctorsArrayList);

            johndoc.setOnClickListener(this::onClick);
            lucasdoc.setOnClickListener(this::onClick);
            ameliadoc.setOnClickListener(this::onClick);
            andrewdoc.setOnClickListener(this::onClick);
            michealdoc.setOnClickListener(this::onClick);
            williamdoc.setOnClickListener(this::onClick);

        return root;


    }
    public void onClick(View v){
        if(v.equals(andrewdoc)){

            Doctors doc1 = new Doctors("Andrew", "doctor.Andrew@gmail.com", "Neurologist",
                    "An Neurologist, graudated from Harvard University. Has more than 10 years of expearince. Won the best doctor prize in Neurology",
                    R.drawable.andrew,19839909);

            doctorsArrayList.add(doc1);
            Intent intent = new Intent(HomeFragment.this, DoctorProfile.class);
        }




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}