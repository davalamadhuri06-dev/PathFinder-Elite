package com.example.pathfinder2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ScholarshipActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship);

        findViewById(R.id.btnLinkNSP).setOnClickListener(v -> openUrl("https://scholarships.gov.in/"));

        findViewById(R.id.btnLinkYasasvi).setOnClickListener(v -> openUrl("https://yet.nta.ac.in/"));

        findViewById(R.id.btnLinkUGC).setOnClickListener(v -> openUrl("https://www.ugc.ac.in/ishanuday/"));

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }


    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
