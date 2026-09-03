package com.example.pathfinder2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SurveyActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText etDream;
    RadioGroup rgAwareness, rgInternet;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        db = new DatabaseHelper(this);
        etDream = findViewById(R.id.etDreamJob);
        rgAwareness = findViewById(R.id.rgAwareness);
        rgInternet = findViewById(R.id.rgInternet);
        btnSubmit = findViewById(R.id.btnSubmitSurvey);

        String studentName = getIntent().getStringExtra("STUDENT_NAME");

        btnSubmit.setOnClickListener(v -> {
            int awarenessId = rgAwareness.getCheckedRadioButtonId();
            int internetId = rgInternet.getCheckedRadioButtonId();
            String dream = etDream.getText().toString().trim();

            if (awarenessId == -1 || internetId == -1 || dream.isEmpty()) {
                Toast.makeText(this, "Please answer all questions", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton rbAware = findViewById(awarenessId);
                RadioButton rbNet = findViewById(internetId);

                boolean inserted = db.insertSurvey(
                        studentName != null ? studentName : "Anonymous",
                        rbAware.getText().toString(),
                        dream,
                        rbNet.getText().toString()
                );

                if (inserted) {
                    // Mandatory AI Analysis Logic
                    String recommendation = "ARTS"; // Default
                    String lowDream = dream.toLowerCase();
                    if (lowDream.contains("tech") || lowDream.contains("code") || lowDream.contains("engineer") || lowDream.contains("space")) {
                        recommendation = "SCIENCE";
                    } else if (lowDream.contains("bank") || lowDream.contains("business") || lowDream.contains("money") || lowDream.contains("ca")) {
                        recommendation = "COMMERCE";
                    }
                    
                    String email = getIntent().getStringExtra("STUDENT_EMAIL");
                    if (email != null) {
                        db.updateRecommendation(email, recommendation);
                    }

                    Toast.makeText(this, "AI Analysis Complete! Welcome to your Personalized Dashboard.", Toast.LENGTH_LONG).show();
                    
                    android.content.Intent intent = new android.content.Intent(this, MainActivity.class);
                    intent.putExtra("USER_NAME", studentName);
                    intent.putExtra("USER_EMAIL", email);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Error saving survey", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }
}
