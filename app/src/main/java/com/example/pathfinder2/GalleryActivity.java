package com.example.pathfinder2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        loadGalleryImages();

        findViewById(R.id.cardSuccessStories).setOnClickListener(v -> openDetail("Success Stories"));
        findViewById(R.id.cardInnovation).setOnClickListener(v -> openDetail("Innovation & Trends"));
        findViewById(R.id.cardEducation).setOnClickListener(v -> openDetail("Global Skillsets"));
        findViewById(R.id.cardSurveying).setOnClickListener(v -> openDetail("Career Surveying"));
        findViewById(R.id.cardMasterclass).setOnClickListener(v -> openDetail("Career Masterclasses"));
    }

    private void loadGalleryImages() {
        // Updated to use your local success story images for all previews
        Glide.with(this).load(R.drawable.software_engineer).centerCrop().into((ImageView) findViewById(R.id.ivGallerySuccess));
        Glide.with(this).load(R.drawable.research_scientist).centerCrop().into((ImageView) findViewById(R.id.ivGalleryInnovation));
        Glide.with(this).load(R.drawable.teacher).centerCrop().into((ImageView) findViewById(R.id.ivGalleryEducation));
        Glide.with(this).load(R.drawable.civil_services_officer).centerCrop().into((ImageView) findViewById(R.id.ivGallerySurvey));
        Glide.with(this).load(R.drawable.mba_student).centerCrop().into((ImageView) findViewById(R.id.ivGalleryMasterclass));
    }

    private void openDetail(String title) {
        Intent intent = new Intent(this, GalleryDetailActivity.class);
        intent.putExtra("TITLE", title);
        startActivity(intent);
    }
}
