package com.example.pathfinder2;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CaseStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_study);

        String career = getIntent().getStringExtra("CAREER_NAME");
        String type = getIntent().getStringExtra("INFO_TYPE");
        if (career == null) career = "Course";
        if (type == null) type = "GENERAL";

        TextView tvTitle = findViewById(R.id.tvCaseStudyTitle);
        TextView tvContent = findViewById(R.id.tvCaseContent);

        String content = "";

        switch (type) {
            case "EXAMS":
                tvTitle.setText(career + " Entrance Mastery");
                content = "🏆 ELITE EXAM GUIDE: " + career.toUpperCase() + "\n\n" +
                        "1. Primary Entrance Exams\n" +
                        "• Level 1: National Standard Tests (Top 5% selection)\n" +
                        "• Level 2: Institute Specific Advanced Exams\n\n" +
                        "2. Strategy for Success\n" +
                        "• 18-month preparation timeline recommended.\n" +
                        "• Focus on analytical reasoning and core fundamentals.\n" +
                        "• Monthly mock tests are mandatory for elite rank.\n\n" +
                        "3. Cut-off Insights\n" +
                        "To secure an elite institute in " + career + ", aim for the 98th percentile minimum.";
                break;

            case "INSTITUTES":
                tvTitle.setText("Top " + career + " Institutes");
                content = "🏛️ ELITE DIRECTORY: " + career.toUpperCase() + "\n\n" +
                        "1. Global Tier-1 Institutes\n" +
                        "• Ivy League Specializations\n" +
                        "• European Excellence Centers\n\n" +
                        "2. National Centers of Excellence\n" +
                        "• Top 3 Institutes in the country for " + career + ".\n" +
                        "• Known for 100% placement and high-research output.\n\n" +
                        "3. Selection Criteria\n" +
                        "• Academic record (85%+)\n" +
                        "• Personal Statement of Purpose\n" +
                        "• Entrance Rank.";
                break;

            case "BOOKS":
                tvTitle.setText(career + " Master Library");
                content = "📚 THE PROFESSIONAL LIBRARY: " + career.toUpperCase() + "\n\n" +
                        "1. Foundational Masterpieces\n" +
                        "• The Core Principles of " + career + " (Vol 1 & 2)\n" +
                        "• Modern Industry Standards Guide\n\n" +
                        "2. Advanced Practical Reading\n" +
                        "• Case Studies in Global " + career + " Strategy\n" +
                        "• The Future of " + career + " and AI integration\n\n" +
                        "3. Digital Resources\n" +
                        "• Access to the Global " + career + " Journal archive.\n" +
                        "• Exclusive PDF notes from Top University Professors.";
                break;

            default:
                tvTitle.setText(career + " Advanced Studies");
                content = "Premium content is being curated for this path.";
                break;
        }
        
        tvContent.setText(content);
    }
}
