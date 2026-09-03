package com.example.pathfinder2;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CareerReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_report);

        findViewById(R.id.btnDownloadReport).setOnClickListener(v -> {
            Toast.makeText(this, "Master Analysis Report Downloaded!", Toast.LENGTH_LONG).show();
        });

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }
}
