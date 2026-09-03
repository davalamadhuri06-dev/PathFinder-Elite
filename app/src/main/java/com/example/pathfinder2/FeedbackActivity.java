package com.example.pathfinder2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FeedbackActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private String userEmail = "anonymous@student.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        db = new DatabaseHelper(this);
        userEmail = getIntent().getStringExtra("USER_EMAIL");
        if (userEmail == null) userEmail = "anonymous@student.com";

        RadioGroup rgUseful = findViewById(R.id.rgUseful);
        RadioGroup rgGallery = findViewById(R.id.rgGallery);
        RadioGroup rgComparison = findViewById(R.id.rgComparison);
        Spinner spinnerFav = findViewById(R.id.spinnerFav);
        EditText etSuggestions = findViewById(R.id.etSuggestions);
        Button btnSubmit = findViewById(R.id.btnSubmitFeedback);

        String[] features = {"Roadmaps", "Gallery", "Comparison", "Success Stories", "Motivational Quotes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, features);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFav.setAdapter(adapter);

        btnSubmit.setOnClickListener(v -> {
            int usefulId = rgUseful.getCheckedRadioButtonId();
            int galleryId = rgGallery.getCheckedRadioButtonId();
            int comparisonId = rgComparison.getCheckedRadioButtonId();
            
            if (usefulId == -1 || galleryId == -1 || comparisonId == -1) {
                Toast.makeText(this, "Please answer all review questions", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton rbUseful = findViewById(usefulId);
            String feedbackSummary = "Useful: " + rbUseful.getText().toString() + 
                                     ", Fav: " + spinnerFav.getSelectedItem().toString();
            String suggest = etSuggestions.getText().toString().trim();

            if (db.saveFeedback(userEmail, rbUseful.getText().toString(), spinnerFav.getSelectedItem().toString(), suggest)) {
                Toast.makeText(this, "Review Submitted! Thank you for the Elite feedback.", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Error saving review", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
