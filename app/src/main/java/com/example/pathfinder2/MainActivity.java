package com.example.pathfinder2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvAppTitle, tvQuoteText, tvQuoteAuthor;
    CardView cardExams, cardCareer, cardScholarship, cardRoadmap, cardCompare;
    BottomNavigationView bottomNav;
    String currentStudentName = "Explorer";
    String currentStudentEmail = "";

    String[] quotes = {
            "Excellence is not a skill. It is an attitude.",
            "Your talent determines what you can do.",
            "The best way to predict the future is to create it.",
            "Success is the sum of small efforts, repeated day in and day out.",
            "Dream big and dare to fail.",
            "The only way to do great work is to love what you do."
    };
    String[] authors = {"— Aristotle", "— Lou Holtz", "— Peter Drucker", "— Robert Collier", "— Norman Vaughan", "— Steve Jobs"};
    Handler quoteHandler = new Handler(Looper.getMainLooper());
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAppTitle = findViewById(R.id.tvAppTitle);
        tvQuoteText = findViewById(R.id.tvQuoteText);
        tvQuoteAuthor = findViewById(R.id.tvQuoteAuthor);
        
        cardExams = findViewById(R.id.cardExams);
        cardCareer = findViewById(R.id.cardCareer);
        cardScholarship = findViewById(R.id.cardScholarship);
        cardRoadmap = findViewById(R.id.cardRoadmap);
        cardCompare = findViewById(R.id.cardCompare);
        bottomNav = findViewById(R.id.bottomNav);

        // Premium Animations
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.fade_in_slide_up);
        findViewById(R.id.cardIdentity).startAnimation(slideUp);
        findViewById(R.id.cardQuote).startAnimation(slideUp);

        loadCarouselImages();
        android.widget.ViewFlipper flipper = findViewById(R.id.viewFlipper);
        if (flipper != null) {
            flipper.startFlipping();
        }

        startQuoteRotation();

        // Receive User Data
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("USER_NAME");
            String email = intent.getStringExtra("USER_EMAIL");
            if (name != null && !name.isEmpty()) {
                currentStudentName = name;
                currentStudentEmail = email;
                TextView tvProfile = findViewById(R.id.tvProfileInitial);
                tvProfile.setText(name.substring(0, 1).toUpperCase());
                
                // Personalized Greeting
                tvAppTitle.setText("Hello, " + name);
                tvAppTitle.animate().scaleX(1.1f).scaleY(1.1f).setDuration(500).withEndAction(() -> {
                    tvAppTitle.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500).start();
                }).start();
            }
        }

        findViewById(R.id.cardProfile).setOnClickListener(v -> {
            openProfile();
        });

        tvAppTitle.setOnLongClickListener(v -> {
            showAdminLoginDialog();
            return true;
        });

        tvAppTitle.setOnClickListener(v -> {
            v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(150).withEndAction(() -> {
                v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
            }).start();
        });

        setupInteractiveCard(cardRoadmap, () -> startActivity(new Intent(this, RoadmapActivity.class)));
        setupInteractiveCard(cardCareer, () -> {
            Intent i = new Intent(this, DashboardActivity.class);
            i.putExtra("USER_NAME", currentStudentName);
            startActivity(i);
        });
        setupInteractiveCard(cardExams, () -> startActivity(new Intent(this, ExamsActivity.class)));
        setupInteractiveCard(cardScholarship, () -> startActivity(new Intent(this, ScholarshipActivity.class)));
        setupInteractiveCard(cardCompare, () -> startActivity(new Intent(this, CareerComparisonActivity.class)));
        
        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            NavigationBottomMenu menu = NavigationBottomMenu.newInstance(currentStudentName, currentStudentEmail);
            menu.show(getSupportFragmentManager(), "NavMenu");
        });

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_trends) {
                startActivity(new Intent(this, TrendingActivity.class));
                return true;
            } else if (id == R.id.nav_academy) {
                startActivity(new Intent(this, ScholarshipActivity.class));
                return true;
            } else if (id == R.id.nav_roadmap) {
                startActivity(new Intent(this, RoadmapActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                openProfile();
                return true;
            }
            return false;
        });
    }

    private void setupInteractiveCard(View card, Runnable action) {
        card.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start();
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                case MotionEvent.ACTION_CANCEL:
                    v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        action.run();
                    }
                    break;
            }
            return true;
        });
    }

    private void openProfile() {
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("STUDENT_NAME", currentStudentName);
        i.putExtra("STUDENT_EMAIL", currentStudentEmail);
        startActivity(i);
    }

    private void loadCarouselImages() {
        // High-resolution vibrant education images for the home carousel - focus on real global success stories
        ImageView iv1 = findViewById(R.id.ivCarousel1);
        ImageView iv2 = findViewById(R.id.ivCarousel2);
        ImageView iv3 = findViewById(R.id.ivCarousel3);

        if (iv1 != null) {
            Glide.with(this).load("https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=1000").centerCrop().into(iv1);
        }
        if (iv2 != null) {
            Glide.with(this).load("https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?w=1000").centerCrop().into(iv2);
        }
        if (iv3 != null) {
            Glide.with(this).load("https://images.unsplash.com/photo-1531482615713-2afd69097998?w=1000").centerCrop().into(iv3);
        }

        // Load Success Story real-world student profiles from local drawables
        ImageView s1 = findViewById(R.id.ivSuccess1);
        ImageView s2 = findViewById(R.id.ivSuccess2);
        ImageView s3 = findViewById(R.id.ivSuccess3);
        ImageView s4 = findViewById(R.id.ivSuccess4);
        ImageView s5 = findViewById(R.id.ivSuccess5);

        if (s1 != null) Glide.with(this).load(R.drawable.software_engineer).circleCrop().into(s1);
        if (s2 != null) Glide.with(this).load(R.drawable.research_scientist).circleCrop().into(s2);
        if (s3 != null) Glide.with(this).load(R.drawable.mba_student).circleCrop().into(s3);
        if (s4 != null) Glide.with(this).load(R.drawable.civil_services_officer).circleCrop().into(s4);
        if (s5 != null) Glide.with(this).load(R.drawable.civil_engineer).circleCrop().into(s5);
    }

    private void startQuoteRotation() {
        quoteHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int index = random.nextInt(quotes.length);
                tvQuoteText.setText(quotes[index]);
                tvQuoteAuthor.setText(authors[index]);
                
                // Add a small fade animation for better interactivity
                tvQuoteText.setAlpha(0f);
                tvQuoteText.animate().alpha(1f).setDuration(500).start();
                
                quoteHandler.postDelayed(this, 8000); // Change every 8 seconds
            }
        }, 8000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        quoteHandler.removeCallbacksAndMessages(null);
    }

    private void showAdminLoginDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_admin_login, null);
        EditText etPin = dialogView.findViewById(R.id.etAdminPin);
        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("UNLOCK", (d, w) -> {
                    String password = etPin.getText().toString().trim();
                    if (password.equalsIgnoreCase("admin123")) {
                        startActivity(new Intent(MainActivity.this, ViewResultsActivity.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }
}
