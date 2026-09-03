package com.example.pathfinder2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PortfolioSubmissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_submission);

        String career = getIntent().getStringExtra("CAREER_NAME");
        TextView tvTitle = findViewById(R.id.tvPortfolioTitle);
        tvTitle.setText(career + " Portfolio Review");

        EditText etLink = findViewById(R.id.etPortfolioLink);
        EditText etNotes = findViewById(R.id.etPortfolioNotes);
        Button btnSubmit = findViewById(R.id.btnSubmitPortfolio);
        ProgressBar progress = findViewById(R.id.uploadProgress);
        LinearLayout successPanel = findViewById(R.id.successPanel);

        btnSubmit.setOnClickListener(v -> {
            String link = etLink.getText().toString().trim();
            if (link.isEmpty()) {
                Toast.makeText(this, "Please provide your portfolio link", Toast.LENGTH_SHORT).show();
                return;
            }

            btnSubmit.setVisibility(View.GONE);
            progress.setVisibility(View.VISIBLE);

            // Simulate real-world upload
            new android.os.Handler().postDelayed(() -> {
                progress.setVisibility(View.GONE);
                successPanel.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Portfolio Uploaded Successfully!", Toast.LENGTH_LONG).show();
            }, 3000);
        });
    }
}
