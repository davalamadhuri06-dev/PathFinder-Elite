package com.example.pathfinder2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AssessmentActivity extends AppCompatActivity {

    private TextView tvCount, tvQuestion, tvFinalScore, tvMessage;
    private RadioGroup rgOptions;
    private RadioButton rb1, rb2, rb3;
    private View layoutScoreCard, layoutSelection;
    private LinearLayout layoutQuestions;
    
    private int currentIdx = 0;
    private int techScore = 0, softScore = 0;
    private List<AssessmentQuestion> activeQuestions = new ArrayList<>();

    private static class AssessmentQuestion {
        String question;
        String[] options;
        String type; // "TECH" or "SOFT"
        AssessmentQuestion(String q, String[] o, String t) {
            this.question = q; this.options = o; this.type = t;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        layoutSelection = findViewById(R.id.layoutThemeSelection);
        layoutQuestions = findViewById(R.id.layoutQuestionArea);
        layoutScoreCard = findViewById(R.id.layoutScoreCard);
        
        tvCount = findViewById(R.id.tvQuestionCount);
        tvQuestion = findViewById(R.id.tvQuestionText);
        rgOptions = findViewById(R.id.rgOptions);
        rb1 = findViewById(R.id.rbOpt1);
        rb2 = findViewById(R.id.rbOpt2);
        rb3 = findViewById(R.id.rbOpt3);
        tvFinalScore = findViewById(R.id.tvFinalScore);
        tvMessage = findViewById(R.id.tvScoreMessage);

        findViewById(R.id.btnStartTest).setOnClickListener(v -> startThemedTest());
        findViewById(R.id.btnNextAssessment).setOnClickListener(v -> handleNext());
        findViewById(R.id.btnBackToProfile).setOnClickListener(v -> finish());
    }

    private void startThemedTest() {
        CheckBox cbCoding = findViewById(R.id.cbCoding);
        CheckBox cbComm = findViewById(R.id.cbCommunication);

        if (!cbCoding.isChecked() && !cbComm.isChecked()) {
            Toast.makeText(this, "Select at least one theme", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cbCoding.isChecked()) {
            activeQuestions.add(new AssessmentQuestion("How comfortable are you with Logic & Coding?", new String[]{"Beginner", "Intermediate", "Advanced"}, "TECH"));
            activeQuestions.add(new AssessmentQuestion("Rate your knowledge of SQL/Databases.", new String[]{"None", "Concepts Only", "Can Write Queries"}, "TECH"));
        }
        if (cbComm.isChecked()) {
            activeQuestions.add(new AssessmentQuestion("How do you feel about Public Speaking?", new String[]{"Nervous", "Okay", "Love it"}, "SOFT"));
            activeQuestions.add(new AssessmentQuestion("Rate your Team Leadership skills.", new String[]{"Follower", "Contributor", "Leader"}, "SOFT"));
        }

        layoutSelection.setVisibility(View.GONE);
        layoutQuestions.setVisibility(View.VISIBLE);
        loadQuestion();
    }

    private void loadQuestion() {
        tvCount.setText("ASSESSING: " + (currentIdx + 1) + "/" + activeQuestions.size());
        AssessmentQuestion q = activeQuestions.get(currentIdx);
        tvQuestion.setText(q.question);
        rb1.setText(q.options[0]);
        rb2.setText(q.options[1]);
        rb3.setText(q.options[2]);
        rgOptions.clearCheck();
    }

    private void handleNext() {
        int selectedId = rgOptions.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        int score = 0;
        if (selectedId == R.id.rbOpt1) score = 10;
        else if (selectedId == R.id.rbOpt2) score = 20;
        else if (selectedId == R.id.rbOpt3) score = 30;

        if (activeQuestions.get(currentIdx).type.equals("TECH")) techScore += score;
        else softScore += score;

        currentIdx++;
        if (currentIdx < activeQuestions.size()) {
            loadQuestion();
        } else {
            showScoreCard();
        }
    }

    private void showScoreCard() {
        layoutQuestions.setVisibility(View.GONE);
        layoutScoreCard.setVisibility(View.VISIBLE);
        
        String result = "Tech: " + techScore + "pts | Soft: " + softScore + "pts";
        tvFinalScore.setText(result);
        
        String msg = "Elite Analysis: ";
        if (techScore > softScore) msg += "Your future lies in Technical Innovation.";
        else msg += "You are a natural Leader and Communicator.";
        
        tvMessage.setText(msg);
    }
}
