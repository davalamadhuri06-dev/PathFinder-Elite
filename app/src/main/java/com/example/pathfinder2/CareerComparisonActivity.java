package com.example.pathfinder2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class CareerComparisonActivity extends AppCompatActivity {

    Spinner spinner1, spinner2;
    Button btnCompare;
    LinearLayout layoutResult;
    TextView tvSalary1, tvSalary2, tvDuration1, tvDuration2, tvSkills1, tvSkills2, tvGrowth1, tvGrowth2, tvEnvironment1, tvEnvironment2, tvOutlook1, tvOutlook2, tvProsCons1, tvProsCons2;

    Map<String, CareerInfo> careerData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_comparison);

        spinner1 = findViewById(R.id.spinnerCareer1);
        spinner2 = findViewById(R.id.spinnerCareer2);
        btnCompare = findViewById(R.id.btnCompare);
        layoutResult = findViewById(R.id.layoutComparisonResult);
        
        tvSalary1 = findViewById(R.id.tvSalaryC1);
        tvSalary2 = findViewById(R.id.tvSalaryC2);
        tvDuration1 = findViewById(R.id.tvDurationC1);
        tvDuration2 = findViewById(R.id.tvDurationC2);
        tvSkills1 = findViewById(R.id.tvSkillsC1);
        tvSkills2 = findViewById(R.id.tvSkillsC2);
        tvGrowth1 = findViewById(R.id.tvGrowthC1);
        tvGrowth2 = findViewById(R.id.tvGrowthC2);
        tvEnvironment1 = findViewById(R.id.tvEnvironmentC1);
        tvEnvironment2 = findViewById(R.id.tvEnvironmentC2);
        tvOutlook1 = findViewById(R.id.tvOutlookC1);
        tvOutlook2 = findViewById(R.id.tvOutlookC2);
        tvProsCons1 = findViewById(R.id.tvProsConsC1);
        tvProsCons2 = findViewById(R.id.tvProsConsC2);

        initData();

        String[] careers = {
            "Software Engineer", "Medical Doctor", "IAS Officer", "Chartered Accountant", 
            "Data Scientist", "UI/UX Designer", "Pilot", "Lawyer", "Chef", "Journalist",
            "Psychologist", "Architect", "Fashion Designer", "Robotics Engineer"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_black, careers);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        btnCompare.setOnClickListener(v -> {
            String c1 = spinner1.getSelectedItem().toString();
            String c2 = spinner2.getSelectedItem().toString();
            compare(c1, c2);
        });

        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = new NavigationBottomMenu();
            menu.show(getSupportFragmentManager(), "NavMenu");
        });
    }

    private void initData() {
        careerData.put("Software Engineer", new CareerInfo("₹8L - ₹60L+", "4 Years", "Coding, AI, Problem Solving", "Very High (25% Growth)", "Tech Hubs / Remote Flexible", "AI Revolution & SaaS", "PROS: High Pay, Creativity\nCONS: Sitting hours, Rapid Change"));
        careerData.put("Medical Doctor", new CareerInfo("₹10L - ₹50L+", "5.5 - 10 Years", "Surgery, Diagnosis, Patience", "Stable (High Demand)", "Hospitals / Private Clinics", "Always High (Healthcare focus)", "PROS: Noble, Job Security\nCONS: Long Hours, High Stress"));
        careerData.put("IAS Officer", new CareerInfo("₹7L - ₹25L", "UPSC Prep (1-3 yrs)", "Ethics, Admin, Governance", "High (Prestige)", "Government Offices / Field", "Influential Policy Making", "PROS: Power & Service\nCONS: Frequent Transfers"));
        careerData.put("Chartered Accountant", new CareerInfo("₹8L - ₹35L", "4-5 Years", "Audit, Taxation, Law", "Stable", "Corporate / Consultancy", "Global Accounting Scope", "PROS: Financial Expertise\nCONS: Heavy Workload in Mar/Sep"));
        careerData.put("Data Scientist", new CareerInfo("₹12L - ₹70L", "4 Years", "Python, ML, Statistics", "Explosive Growth", "Modern Tech Hubs", "Data-Driven Decisions", "PROS: High Salary, Solving Puzzles\nCONS: Continuous Re-learning"));
        careerData.put("UI/UX Designer", new CareerInfo("₹6L - ₹40L", "3-4 Years", "Design, Figma, Psychology", "Growing (Digital First)", "Creative Studios / Tech", "Experience Economy Focus", "PROS: Visual Creativity\nCONS: Subjective Feedback"));
        careerData.put("Pilot", new CareerInfo("₹20L - ₹80L", "2-3 Years", "Aviation, Navigation, Physics", "Moderate (Tourism Rise)", "Global / Cockpit", "Global Connectivity", "PROS: Travel, High Status\nCONS: High Training Cost"));
        careerData.put("Lawyer", new CareerInfo("₹5L - ₹45L", "5 Years", "Legal Logic, Research, Oratory", "Steady", "Courts / Corporate Firms", "Corporate Law Booming", "PROS: Social Impact, Intellectual\nCONS: Competitive Entry"));
        careerData.put("Chef", new CareerInfo("₹4L - ₹25L", "3 Years", "Culinary Arts, Management", "Good (Hospitality)", "Luxury Hotels / Restaurants", "Culinary Tourism Rise", "PROS: Artistic Expression\nCONS: Physically Demanding"));
        careerData.put("Journalist", new CareerInfo("₹3L - ₹15L", "3 Years", "Research, Writing, Ethics", "Competitive", "Field / Media Houses", "Digital Media Expansion", "PROS: Dynamic, Social Voice\nCONS: Entry Level Pay is Low"));
        careerData.put("Psychologist", new CareerInfo("₹4L - ₹20L", "5 Years", "Counseling, Empathy, Research", "Rising (Mental Health)", "Clinics / Private Practice", "Wellness Revolution", "PROS: Emotional Fulfillment\nCONS: High Emotional Load"));
        careerData.put("Architect", new CareerInfo("₹5L - ₹30L", "5 Years", "Structural Design, CAD, Math", "Steady", "Design Firms / On-site", "Smart City Infrastructure", "PROS: Building Legacies\nCONS: Slow Career Start"));
        careerData.put("Fashion Designer", new CareerInfo("₹4L - ₹35L", "4 Years", "Textiles, Trends, Marketing", "Fast-Paced", "Fashion Houses / Studio", "E-commerce Boom", "PROS: Glamour, Artistic\nCONS: Intense Competition"));
        careerData.put("Robotics Engineer", new CareerInfo("₹10L - ₹55L", "4 Years", "Hardware, AI, ROS, Sensors", "High (Industrial 4.0)", "R&D Labs / Manufacturing", "Automation & Space Era", "PROS: Cutting Edge Tech\nCONS: Highly Complex Fields"));
    }

    private void compare(String c1, String c2) {
        CareerInfo i1 = careerData.get(c1);
        CareerInfo i2 = careerData.get(c2);

        if (i1 != null && i2 != null) {
            layoutResult.setVisibility(View.VISIBLE);
            layoutResult.setAlpha(0f);
            layoutResult.animate().alpha(1f).setDuration(500).start();

            tvSalary1.setText(i1.salary);
            tvSalary2.setText(i2.salary);
            
            tvDuration1.setText(i1.duration);
            tvDuration2.setText(i2.duration);
            
            tvSkills1.setText(i1.skills);
            tvSkills2.setText(i2.skills);

            tvGrowth1.setText(i1.growth);
            tvGrowth2.setText(i2.growth);

            tvEnvironment1.setText(i1.environment);
            tvEnvironment2.setText(i2.environment);

            tvOutlook1.setText(i1.outlook);
            tvOutlook2.setText(i2.outlook);

            tvProsCons1.setText(i1.prosCons);
            tvProsCons2.setText(i2.prosCons);
        }
    }

    static class CareerInfo {
        String salary, duration, skills, growth, environment, outlook, prosCons;
        CareerInfo(String s, String d, String sk, String g, String e, String o, String pc) {
            this.salary = s;
            this.duration = d;
            this.skills = sk;
            this.growth = g;
            this.environment = e;
            this.outlook = o;
            this.prosCons = pc;
        }
    }
}
