package com.example.pathfinder2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ExamsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }

}
