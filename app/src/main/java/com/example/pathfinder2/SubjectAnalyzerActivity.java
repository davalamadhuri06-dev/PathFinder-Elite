package com.example.pathfinder2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SubjectAnalyzerActivity extends AppCompatActivity {

    CheckBox cbMath, cbPhysics, cbBiology, cbEconomics, cbHistory;
    Button btnAnalyze;
    CardView cardResult;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_analyzer);

        cbMath = findViewById(R.id.cbMath);
        cbPhysics = findViewById(R.id.cbPhysics);
        cbBiology = findViewById(R.id.cbBiology);
        cbEconomics = findViewById(R.id.cbEconomics);
        cbHistory = findViewById(R.id.cbHistory);
        btnAnalyze = findViewById(R.id.btnAnalyze);
        cardResult = findViewById(R.id.cardSubjectResult);
        tvResult = findViewById(R.id.tvSubjectResult);

        btnAnalyze.setOnClickListener(v -> analyze());

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }

    private void analyze() {
        StringBuilder sb = new StringBuilder();
        if (cbMath.isChecked() && cbPhysics.isChecked()) {
            sb.append("Recommended: Engineering (MPC Path), Data Science, or Architecture.\n\n");
        }
        if (cbBiology.isChecked() && cbPhysics.isChecked()) {
            sb.append("Recommended: Medical (BiPC Path), Biotechnology, or Nursing.\n\n");
        }
        if (cbEconomics.isChecked() && cbMath.isChecked()) {
            sb.append("Recommended: Chartered Accountancy, Investment Banking, or Economics.\n\n");
        }
        if (cbHistory.isChecked() && cbEconomics.isChecked()) {
            sb.append("Recommended: Civil Services (IAS), Law, or Corporate Strategy.\n\n");
        }
        if (cbHistory.isChecked() && !cbMath.isChecked() && !cbPhysics.isChecked()) {
            sb.append("Recommended: Journalism, Psychology, or Design Arts.\n\n");
        }

        if (sb.length() == 0) {
            tvResult.setText("Select a combination of subjects for an elite career recommendation.");
        } else {
            tvResult.setText(sb.toString());
        }
        cardResult.setVisibility(View.VISIBLE);
    }
}
