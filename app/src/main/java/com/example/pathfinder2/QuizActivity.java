package com.example.pathfinder2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    TextView tvQuestion, tvNumber, tvResult;
    Button btn1, btn2, btn3, btnFinish;
    LinearLayout layoutOptions, layoutResult;
    
    int currentIdx = 0;
    int scienceScore = 0, commerceScore = 0, artsScore = 0;

    String[] questions = {
        "Which activity excites you the most?",
        "How do you prefer solving problems?",
        "What is your ideal workspace?",
        "Which subject do you naturally enjoy?"
    };

    String[][] options = {
        {"Building software or gadgets", "Managing money or business", "Creating art or writing stories"},
        {"Using logic and math", "Analyzing data and trends", "Understanding human emotions"},
        {"A high-tech laboratory", "A corporate boardroom", "A creative studio or field"},
        {"Physics & Mathematics", "Economics & Accounts", "History & Psychology"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = findViewById(R.id.tvQuestionText);
        tvNumber = findViewById(R.id.tvQuestionNumber);
        tvResult = findViewById(R.id.tvResultPath);
        
        btn1 = findViewById(R.id.btnOpt1);
        btn2 = findViewById(R.id.btnOpt2);
        btn3 = findViewById(R.id.btnOpt3);
        btnFinish = findViewById(R.id.btnFinishQuiz);
        
        layoutOptions = findViewById(R.id.layoutOptions);
        layoutResult = findViewById(R.id.layoutResult);

        updateQuestion();

        btn1.setOnClickListener(v -> { scienceScore++; next(); });
        btn2.setOnClickListener(v -> { commerceScore++; next(); });
        btn3.setOnClickListener(v -> { artsScore++; next(); });

        btnFinish.setOnClickListener(v -> finish());
    }

    private void updateQuestion() {
        if (currentIdx < questions.length) {
            tvNumber.setText("ANALYSIS: STEP " + (currentIdx + 1) + " OF " + questions.length);
            tvQuestion.setText(questions[currentIdx]);
            btn1.setText(options[currentIdx][0]);
            btn2.setText(options[currentIdx][1]);
            btn3.setText(options[currentIdx][2]);
        } else {
            showResult();
        }
    }

    private void next() {
        currentIdx++;
        updateQuestion();
    }

    private void showResult() {
        layoutOptions.setVisibility(View.GONE);
        layoutResult.setVisibility(View.VISIBLE);

        if (scienceScore >= commerceScore && scienceScore >= artsScore) {
            tvResult.setText("SCIENCE & TECHNOLOGY");
        } else if (commerceScore >= scienceScore && commerceScore >= artsScore) {
            tvResult.setText("COMMERCE & BUSINESS");
        } else {
            tvResult.setText("ARTS & HUMANITIES");
        }
    }
}
