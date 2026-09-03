package com.example.pathfinder2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class TrendingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        findViewById(R.id.cardTrendAI).setOnClickListener(v -> {
            Intent intent = new Intent(this, CareerDetailsActivity.class);
            intent.putExtra("CAREER_TYPE", "TRENDING_AI");
            startActivity(intent);
        });

        findViewById(R.id.cardTrendEnergy).setOnClickListener(v -> {
            Intent intent = new Intent(this, CareerDetailsActivity.class);
            intent.putExtra("CAREER_TYPE", "TRENDING_ENERGY");
            startActivity(intent);
        });

        findViewById(R.id.cardTrendUX).setOnClickListener(v -> {
            Intent intent = new Intent(this, CareerDetailsActivity.class);
            intent.putExtra("CAREER_TYPE", "TRENDING_UX");
            startActivity(intent);
        });

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }
}
