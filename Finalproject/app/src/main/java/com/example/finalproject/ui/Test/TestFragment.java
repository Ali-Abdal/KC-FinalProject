package com.example.finalproject.ui.Test;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.finalproject.QnAtest;
import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentAboutusBinding;

public class TestFragment extends Fragment implements View.OnClickListener {

    private FragmentAboutusBinding binding;

    int x=0;
    int totalQuestion = QnAtest.question.length;
    int currentQuestionIndex = 0;
    int score = 0;
    String selectedAnswer= "";
    TextView QnA,txtresult;
    Button ansa,ansb,ansc,ansd,submit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_aboutus, container, false);

        txtresult = root.findViewById(R.id.result);
        QnA = root.findViewById(R.id.txtqna);
        ansa = root.findViewById(R.id.btnA);
        ansb = root.findViewById(R.id.btnB);
        ansc = root.findViewById(R.id.btnC);
        ansd = root.findViewById(R.id.btnD);
        submit = root.findViewById(R.id.btnsubmit);


        ansa.setOnClickListener(this);
        ansb.setOnClickListener(this::onClick);
        ansc.setOnClickListener(this::onClick);
        ansd.setOnClickListener(this::onClick);
        submit.setOnClickListener(this::onClick);

        txtresult.setText("Total questions " +totalQuestion);

        loadNewQuestion();

        return root;
    }

    void loadNewQuestion(){
        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        QnA.setText(QnAtest.question[currentQuestionIndex]);
        ansa.setText(QnAtest.choices[currentQuestionIndex][0]);
        ansb.setText(QnAtest.choices[currentQuestionIndex][1]);
        ansc.setText(QnAtest.choices[currentQuestionIndex][2]);
        ansd.setText(QnAtest.choices[currentQuestionIndex][3]);
    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Result";
        }
        else{
            passStatus = "Result";
        }

        new AlertDialog.Builder(getActivity())
                .setTitle(passStatus)
                .setMessage("Seems like you have a flu")
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();

    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.btnsubmit){

            if (selectedAnswer.equals(QnAtest.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else{
            selectedAnswer = clickedButton.getText().toString();

        }

    }
}