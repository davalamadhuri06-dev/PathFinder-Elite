package com.example.pathfinder2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ResumeBuilderActivity extends AppCompatActivity {

    EditText etName, etExpertise, etSkills;
    Button btnGenerate, btnDownload;
    CardView cardPreview;
    TextView tvPrevName, tvPrevExpertise, tvPrevSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_builder);

        etName = findViewById(R.id.etResumeName);
        etExpertise = findViewById(R.id.etResumeExpertise);
        etSkills = findViewById(R.id.etResumeSkills);
        btnGenerate = findViewById(R.id.btnGenerateResume);
        btnDownload = findViewById(R.id.btnDownloadResume);
        cardPreview = findViewById(R.id.cardResumePreview);
        
        tvPrevName = findViewById(R.id.tvPrevName);
        tvPrevExpertise = findViewById(R.id.tvPrevExpertise);
        tvPrevSkills = findViewById(R.id.tvPrevSkills);

        btnGenerate.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String expertise = etExpertise.getText().toString().trim();
            String skills = etSkills.getText().toString().trim();

            if (name.isEmpty() || expertise.isEmpty() || skills.isEmpty()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            } else {
                tvPrevName.setText(name.toUpperCase());
                tvPrevExpertise.setText("EXPERTISE: " + expertise);
                tvPrevSkills.setText("CORE SKILLS: " + skills);
                cardPreview.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Elite Resume Generated!", Toast.LENGTH_SHORT).show();
            }
        });

        btnDownload.setOnClickListener(v -> {
            Toast.makeText(this, "Downloading PDF to your device...", Toast.LENGTH_LONG).show();
            // In a real app, logic to save view as PDF would go here
        });

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }
}
