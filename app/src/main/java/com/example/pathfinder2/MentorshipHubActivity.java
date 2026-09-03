package com.example.pathfinder2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MentorshipHubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentorship_hub);

        findViewById(R.id.btnStartChat).setOnClickListener(v -> {
            Toast.makeText(this, "Connecting to Expert Mentor...", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btnCertificate).setOnClickListener(v -> {
            Toast.makeText(this, "Generating your Certificate of Excellence...", Toast.LENGTH_LONG).show();
        });

        findViewById(R.id.btnLibrary).setOnClickListener(v -> {
            Toast.makeText(this, "Opening Premium Resource Library...", Toast.LENGTH_SHORT).show();
        });
    }
}
