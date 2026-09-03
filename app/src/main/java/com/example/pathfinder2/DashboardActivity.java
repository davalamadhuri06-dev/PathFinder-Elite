package com.example.pathfinder2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        String name = getIntent().getStringExtra("USER_NAME");
        if (name != null && !name.isEmpty()) {
            tvWelcome.setText(getString(R.string.dashboard_welcome, name));
        }

        findViewById(R.id.cardScience).setOnClickListener(v -> {
            Intent intent = new Intent(this, CareerDetailsActivity.class);
            intent.putExtra("CAREER_TYPE", "SCIENCE");
            intent.putExtra("STUDENT_NAME", name);
            startActivity(intent);
        });

        findViewById(R.id.cardCommerce).setOnClickListener(v -> {
            Intent intent = new Intent(this, CareerDetailsActivity.class);
            intent.putExtra("CAREER_TYPE", "COMMERCE");
            intent.putExtra("STUDENT_NAME", name);
            startActivity(intent);
        });

        findViewById(R.id.cardArts).setOnClickListener(v -> {
            Intent intent = new Intent(this, CareerDetailsActivity.class);
            intent.putExtra("CAREER_TYPE", "ARTS");
            intent.putExtra("STUDENT_NAME", name);
            startActivity(intent);
        });

        findViewById(R.id.cardAI).setOnClickListener(v -> {
            Intent intent = new Intent(this, CareerDetailsActivity.class);
            intent.putExtra("CAREER_TYPE", "TRENDING_AI");
            intent.putExtra("STUDENT_NAME", name);
            startActivity(intent);
        });

        findViewById(R.id.cardEnergy).setOnClickListener(v -> {
            Intent intent = new Intent(this, CareerDetailsActivity.class);
            intent.putExtra("CAREER_TYPE", "TRENDING_ENERGY");
            intent.putExtra("STUDENT_NAME", name);
            startActivity(intent);
        });

        findViewById(R.id.cardUX).setOnClickListener(v -> {
            Intent intent = new Intent(this, CareerDetailsActivity.class);
            intent.putExtra("CAREER_TYPE", "TRENDING_UX");
            intent.putExtra("STUDENT_NAME", name);
            startActivity(intent);
        });

        findViewById(R.id.cardVocational).setOnClickListener(v -> {
            Intent intent = new Intent(this, CareerDetailsActivity.class);
            intent.putExtra("CAREER_TYPE", "VOCATIONAL");
            intent.putExtra("STUDENT_NAME", name);
            startActivity(intent);
        });

        findViewById(R.id.btnScholarships).setOnClickListener(v -> {
            Intent intent = new Intent(this, ScholarshipActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }
}
