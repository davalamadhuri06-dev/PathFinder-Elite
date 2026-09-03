package com.example.pathfinder2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CareerDetailsActivity extends AppCompatActivity {

    DatabaseHelper db;
    String studentName = "Guest";
    String careerTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_details);

        db = new DatabaseHelper(this);
        studentName = getIntent().getStringExtra("STUDENT_NAME");
        if (studentName == null) studentName = "Elite Student";

        TextView tvTitle = findViewById(R.id.tvCareerTitle);
        TextView tvInfo = findViewById(R.id.tvCareerInfo);
        TextView tvSalary = findViewById(R.id.tvSalary);
        TextView tvDuration = findViewById(R.id.tvDuration);
        TextView tvDifficulty = findViewById(R.id.tvDifficulty);
        TextView tvColleges = findViewById(R.id.tvColleges);
        TextView tvExams = findViewById(R.id.tvExams);
        TextView tvStory = findViewById(R.id.tvStory);
        TextView tvBooks = findViewById(R.id.tvBooks);
        TextView tvRoadmap = findViewById(R.id.tvRoadmap);
        TextView tvDayInLife = findViewById(R.id.tvDayInLife);
        TextView tvSalaryGrowth = findViewById(R.id.tvSalaryGrowth);
        Button btnApply = findViewById(R.id.btnRegisterInterest);
        androidx.cardview.widget.CardView cardPremium = findViewById(R.id.cardPremiumPanel);

        String type = getIntent().getStringExtra("CAREER_TYPE");
        if (type == null) type = "";

        switch (type) {
            case "SCIENCE":
                careerTitle = getString(R.string.career_sci_title);
                tvTitle.setText(careerTitle);
                tvInfo.setText(R.string.career_sci_info);
                tvSalary.setText(R.string.career_sci_salary);
                tvDuration.setText(R.string.career_sci_duration);
                tvDifficulty.setText(R.string.career_sci_difficulty);
                tvColleges.setText(R.string.career_sci_colleges);
                tvExams.setText(R.string.career_sci_exams);
                tvStory.setText(R.string.career_sci_story);
                tvBooks.setText(R.string.career_sci_books);
                tvRoadmap.setText(R.string.career_sci_roadmap);
                tvDayInLife.setText(R.string.career_sci_day);
                tvSalaryGrowth.setText(R.string.growth_high);
                break;
            case "COMMERCE":
                careerTitle = getString(R.string.career_com_title);
                tvTitle.setText(careerTitle);
                tvInfo.setText(R.string.career_com_info);
                tvSalary.setText(R.string.career_com_salary);
                tvDuration.setText(R.string.career_com_duration);
                tvDifficulty.setText(R.string.career_com_difficulty);
                tvColleges.setText(R.string.career_com_colleges);
                tvExams.setText(R.string.career_com_exams);
                tvStory.setText(R.string.career_com_story);
                tvBooks.setText(R.string.career_com_books);
                tvRoadmap.setText(R.string.career_com_roadmap);
                tvDayInLife.setText(R.string.career_com_day);
                tvSalaryGrowth.setText(R.string.growth_medium);
                break;
            case "ARTS":
                careerTitle = getString(R.string.career_art_title);
                tvTitle.setText(careerTitle);
                tvInfo.setText(R.string.career_art_info);
                tvSalary.setText(R.string.career_art_salary);
                tvDuration.setText(R.string.career_art_duration);
                tvDifficulty.setText(R.string.career_art_difficulty);
                tvColleges.setText(R.string.career_art_colleges);
                tvExams.setText(R.string.career_art_exams);
                tvStory.setText(R.string.career_art_story);
                tvBooks.setText(R.string.career_art_books);
                tvRoadmap.setText(R.string.career_art_roadmap);
                tvDayInLife.setText(R.string.career_art_day);
                tvSalaryGrowth.setText(R.string.growth_medium);
                break;
            case "TRENDING_AI":
                careerTitle = getString(R.string.trending_ai_title);
                tvTitle.setText(careerTitle);
                tvInfo.setText(R.string.trending_ai_info);
                tvSalary.setText(R.string.trending_ai_salary);
                tvDuration.setText(R.string.trending_ai_duration);
                tvDifficulty.setText("Ultra Elite");
                tvColleges.setText(R.string.trending_ai_colleges);
                tvExams.setText("• SAT/ACT\n• JEE Advanced\n• GRE");
                tvStory.setText("\"Kunal used PathFinder to learn Python and is now an AI Lead at OpenAI.\"");
                tvBooks.setText("• Deep Learning by Ian Goodfellow\n• AI: A Modern Approach");
                tvRoadmap.setText(R.string.career_ai_roadmap);
                tvDayInLife.setText(R.string.career_ai_day);
                tvSalaryGrowth.setText("Exponential (Global Demand)");
                break;
            case "TRENDING_ENERGY":
                careerTitle = getString(R.string.trending_energy_title);
                tvTitle.setText(careerTitle);
                tvInfo.setText(R.string.trending_energy_info);
                tvSalary.setText(R.string.trending_energy_salary);
                tvDuration.setText(R.string.trending_energy_duration);
                tvDifficulty.setText("Elite");
                tvColleges.setText(R.string.trending_energy_colleges);
                tvExams.setText("• GATE (Green Energy)\n• University Entrance");
                tvStory.setText("\"Priya built a solar village project after researching Sustainable Energy here.\"");
                tvBooks.setText("• Renewable Energy by Bentley\n• Sustainable Energy - Without the Hot Air");
                tvRoadmap.setText(R.string.career_energy_roadmap);
                tvDayInLife.setText(R.string.career_energy_day);
                tvSalaryGrowth.setText(R.string.growth_high);
                break;
            case "TRENDING_UX":
                careerTitle = getString(R.string.trending_ux_title);
                tvTitle.setText(careerTitle);
                tvInfo.setText(R.string.trending_ux_info);
                tvSalary.setText(R.string.trending_ux_salary);
                tvDuration.setText(R.string.trending_ux_duration);
                tvDifficulty.setText("Modern Elite");
                tvColleges.setText(R.string.trending_ux_colleges);
                tvExams.setText("• NID DAT\n• UCEED\n• Portfolio Review");
                tvStory.setText("\"Arjun designed this very app's interface after choosing UI/UX Psychology!\"");
                tvBooks.setText("• Don't Make Me Think by Steve Krug\n• The Design of Everyday Things");
                tvRoadmap.setText(R.string.career_ux_roadmap);
                tvDayInLife.setText(R.string.career_ux_day);
                tvSalaryGrowth.setText(R.string.growth_high);
                break;
            case "VOCATIONAL":
                careerTitle = getString(R.string.career_voc_title);
                tvTitle.setText(careerTitle);
                tvInfo.setText(R.string.career_voc_info);
                tvSalary.setText(R.string.career_voc_salary);
                tvDuration.setText(R.string.career_voc_duration);
                tvDifficulty.setText(R.string.career_voc_difficulty);
                tvColleges.setText(R.string.career_voc_colleges);
                tvExams.setText(R.string.career_voc_exams);
                tvStory.setText(R.string.career_voc_story);
                tvBooks.setText(R.string.career_voc_books);
                tvRoadmap.setText(R.string.career_voc_roadmap);
                tvDayInLife.setText(R.string.career_voc_day);
                tvSalaryGrowth.setText(R.string.growth_medium);
                break;
        }

        btnApply.setOnClickListener(v -> {
            // Simulate Eligibility Check then Apply
            showEligibilityDialog();
        });

        // Dynamic Check: If this specific course is approved, unlock premium panel
        if (db.isCourseMentorshipApproved(studentName, careerTitle)) {
            btnApply.setVisibility(View.GONE);
            cardPremium.setVisibility(View.VISIBLE);
            
            // Add premium interactions
            findViewById(R.id.btnPremiumExams).setOnClickListener(v -> {
                Intent examsIntent = new Intent(this, CaseStudyActivity.class);
                examsIntent.putExtra("CAREER_NAME", careerTitle);
                examsIntent.putExtra("INFO_TYPE", "EXAMS");
                startActivity(examsIntent);
            });
            
            findViewById(R.id.btnPremiumInstitutes).setOnClickListener(v -> {
                Intent instIntent = new Intent(this, CaseStudyActivity.class);
                instIntent.putExtra("CAREER_NAME", careerTitle);
                instIntent.putExtra("INFO_TYPE", "INSTITUTES");
                startActivity(instIntent);
            });

            findViewById(R.id.btnPremiumBooks).setOnClickListener(v -> {
                Intent booksIntent = new Intent(this, CaseStudyActivity.class);
                booksIntent.putExtra("CAREER_NAME", careerTitle);
                booksIntent.putExtra("INFO_TYPE", "BOOKS");
                startActivity(booksIntent);
            });
        }

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }

    private void showEligibilityDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Mentorship Request");
        builder.setMessage("Request an elite mentor for the " + careerTitle + " path? Admin will review and approve your profile.");
        builder.setPositiveButton("REQUEST MENTOR", (dialog, which) -> {
            boolean success = db.applyForCourse(studentName, careerTitle, "PENDING");
            if (success) {
                Toast.makeText(this, "REQUEST SENT: Awaiting Admin Approval", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

}
