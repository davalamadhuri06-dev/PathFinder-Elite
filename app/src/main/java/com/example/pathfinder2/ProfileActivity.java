package com.example.pathfinder2;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    DatabaseHelper db;
    TextView tvName, tvEmail, tvNoApps;
    ImageView ivProfile;
    LinearLayout layoutApps;
    String currentName;

    // Photo Picker Launcher
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri != null) {
                    // Update UI immediately
                    Glide.with(this).load(uri).circleCrop().into(ivProfile);
                    // Save for persistence
                    saveProfilePhoto(uri.toString());
                    Toast.makeText(this, "Elite Profile Updated!", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new DatabaseHelper(this);
        tvName = findViewById(R.id.tvProfileName);
        tvEmail = findViewById(R.id.tvProfileEmail);
        tvNoApps = findViewById(R.id.tvNoApps);
        layoutApps = findViewById(R.id.layoutAppStatus);
        ivProfile = findViewById(R.id.ivProfilePic);

        currentName = getIntent().getStringExtra("STUDENT_NAME");
        String email = getIntent().getStringExtra("STUDENT_EMAIL");

        tvName.setText(currentName != null ? currentName : getString(R.string.profile_default_name));
        tvEmail.setText(email != null ? email : getString(R.string.profile_default_email));

        // Load Elite Profile Picture (User-saved or Default)
        loadProfilePhoto();

        // Allow user to change photo
        ivProfile.setOnClickListener(v -> {
            pickMedia.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        });

        findViewById(R.id.btnGoFeedback).setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(this, FeedbackActivity.class);
            intent.putExtra("USER_EMAIL", email);
            startActivity(intent);
        });

        loadApplications();
    }

    private void saveProfilePhoto(String uriString) {
        SharedPreferences prefs = getSharedPreferences("ElitePrefs", MODE_PRIVATE);
        prefs.edit().putString("PROFILE_PHOTO_" + currentName, uriString).apply();
    }

    private void loadProfilePhoto() {
        SharedPreferences prefs = getSharedPreferences("ElitePrefs", MODE_PRIVATE);
        String savedUri = prefs.getString("PROFILE_PHOTO_" + currentName, null);

        if (savedUri != null) {
            Glide.with(this).load(Uri.parse(savedUri)).circleCrop().into(ivProfile);
        } else {
            // Default elite photo
            Glide.with(this)
                .load("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?w=400")
                .circleCrop()
                .into(ivProfile);
        }
    }


    private void loadApplications() {
        Cursor res = db.getApplicationsForStudent(currentName);
        if (res.getCount() > 0) {
            tvNoApps.setVisibility(View.GONE);
            while (res.moveToNext()) {
                addAppCard(res.getString(2), res.getString(3));
            }
        }
        res.close();
    }

    private void addAppCard(String course, String status) {
        CardView card = new CardView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 32);
        card.setLayoutParams(params);
        card.setRadius(48f);
        card.setCardElevation(4f);

        LinearLayout inner = new LinearLayout(this);
        inner.setOrientation(LinearLayout.VERTICAL);
        inner.setPadding(48, 48, 48, 48);
        inner.setBackgroundResource(R.drawable.premium_card_bg);

        TextView tvCourse = new TextView(this);
        tvCourse.setText(course);
        tvCourse.setTextSize(18);
        tvCourse.setTextColor(ContextCompat.getColor(this, R.color.text_title));
        tvCourse.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);

        TextView tvStatus = new TextView(this);
        tvStatus.setText(getString(R.string.profile_status_format, status));
        tvStatus.setTextSize(14);
        tvStatus.setPadding(0, 8, 0, 0);
        tvStatus.setTextColor(status.contains("APPROVED") ? 
                ContextCompat.getColor(this, R.color.accent_vibrant) : 
                ContextCompat.getColor(this, R.color.text_light));

        inner.addView(tvCourse);
        inner.addView(tvStatus);
        card.addView(inner);
        layoutApps.addView(card);
    }
}
