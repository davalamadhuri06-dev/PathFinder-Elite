package com.example.pathfinder2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.HashMap;
import java.util.Map;

public class SkillGapActivity extends AppCompatActivity {

    Spinner spinner;
    Button btn;
    CardView cardResult;
    TextView tvDetails;

    Map<String, String> gapData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_gap);

        spinner = findViewById(R.id.spinnerGapCareer);
        btn = findViewById(R.id.btnAnalyzeGap);
        cardResult = findViewById(R.id.cardGapResult);
        tvDetails = findViewById(R.id.tvGapDetails);

        initData();

        String[] careers = {"Software Engineer", "Medical Doctor", "IAS Officer", "Chartered Accountant", "UI/UX Designer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, careers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btn.setOnClickListener(v -> {
            String career = spinner.getSelectedItem().toString();
            cardResult.setVisibility(View.VISIBLE);
            tvDetails.setText(gapData.get(career));
        });

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }

    private void initData() {
        gapData.put("Software Engineer", "Current: Basic Logic\nGap: Data Structures (DSA), Python, Version Control (Git).\nAction: Start LeetCode today!");
        gapData.put("Medical Doctor", "Current: 12th BiPC\nGap: Anatomy, Bio-Chemistry, MCQ Speed.\nAction: Join NEET Test Series.");
        gapData.put("IAS Officer", "Current: Graduation\nGap: Ethics, Current Affairs, World History.\nAction: Read 'The Hindu' daily.");
        gapData.put("Chartered Accountant", "Current: Commerce\nGap: Direct Tax Laws, Auditing Standards.\nAction: Register for ICAI Foundation.");
        gapData.put("UI/UX Designer", "Current: Creativity\nGap: Figma, User Research, Prototyping.\nAction: Build a 3-page case study.");
    }
}
