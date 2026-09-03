package com.example.pathfinder2;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import java.util.ArrayList;
import java.util.List;

public class GalleryDetailActivity extends AppCompatActivity {

    VideoView videoView;
    ImageView ivPlaceholder;
    View btnPlay;
    RecyclerView rvImages;
    TextView tvTitle, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);

        videoView = findViewById(R.id.videoView);
        ivPlaceholder = findViewById(R.id.ivVideoPlaceholder);
        btnPlay = findViewById(R.id.btnPlayVideo);
        rvImages = findViewById(R.id.rvImages);
        tvTitle = findViewById(R.id.tvDetailTitle);
        tvDescription = findViewById(R.id.tvVideoLabel);

        // Load attractive video placeholder
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?w=800")
            .centerCrop()
            .into(ivPlaceholder);

        String category = getIntent().getStringExtra("TITLE");
        tvTitle.setText(category != null ? category : "Elite Gallery");

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        setupCategoryData(category);
    }

    private void setupCategoryData(String category) {
        String videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        String description = "Elite Success Album";
        List<StudentProfile> profiles = new ArrayList<>();

        // Real high-quality International student success images from Unsplash - Focused on diverse foreigners as requested
        String[] successUrls = {
            "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=400", // Team Collaboration
            "https://images.unsplash.com/photo-1523240715639-99f840e5362e?w=400", // Joyful Students
            "https://images.unsplash.com/photo-1543269865-cbf427effbad?w=400", // Group Achievement
            "https://images.unsplash.com/photo-1529333166437-7750a6dd5a70?w=400", // Graduation Success
            "https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?w=400", // Innovation Lab
            "https://images.unsplash.com/photo-1519389950473-47ba0277781c?w=400", // Business Strategy
            "https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=400", // Mentorship Success
            "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400", // Professional Leadership
            "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=400", // Academic Excellence
            "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=400", // Digital Workspace
            "https://images.unsplash.com/photo-1531482615713-2afd69097998?w=400", // Modern Learning
            "https://images.unsplash.com/photo-1524178232363-1fb2b075b655?w=400"  // Global Seminar
        };
        
        String[] names = {"Thomas A.", "Emma L.", "David J.", "Sophia W.", "James B.", "Olivia H.", "Robert M.", "Isabella K.", "William P.", "Mia S.", "Alexander G.", "Charlotte F."};

        if ("Success Stories".equals(category)) {
            videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";
            description = "Real Student Achievement Wall";
            
            // Using your specific local images with the names from MainActivity
            profiles.add(new StudentProfile("Karan (IIT Bombay)", R.drawable.software_engineer));
            profiles.add(new StudentProfile("Vikram (EdTech CEO)", R.drawable.civil_engineer));
            profiles.add(new StudentProfile("Sarah (Genomic Res.)", R.drawable.research_scientist));
            profiles.add(new StudentProfile("Rahul (Google Ldn)", R.drawable.mba_student));
            profiles.add(new StudentProfile("Neha (ISRO Scientist)", R.drawable.teacher));
            profiles.add(new StudentProfile("Ananya (UPSC Ranker)", R.drawable.civil_services_officer));
            
            // Adding more placeholders if needed
            for (int i = 0; i < 6; i++) {
                profiles.add(new StudentProfile(names[i], successUrls[i]));
            }
        } else if ("Innovation & Trends".equals(category)) {
            videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4";
            description = "Future Tech & AI Innovation";
            
            // AI / Tech futuristic looking images
            String[] techUrls = {
                "https://images.unsplash.com/photo-1485827404703-89b55fcc595e?w=400", // Robotics
                "https://images.unsplash.com/photo-1677442136019-21780ecad995?w=400", // AI brain
                "https://images.unsplash.com/photo-1581091226825-a6a2a5aee158?w=400", // Lab
                "https://images.unsplash.com/photo-1507146153580-69a1fe6d8aa1?w=400", // Coding
                "https://images.unsplash.com/photo-1518770660439-4636190af475?w=400", // Circuit
                "https://images.unsplash.com/photo-1531297484001-80022131f5a1?w=400", // Laptop tech
                "https://images.unsplash.com/photo-1550751827-4bd374c3f58b?w=400", // Cyber
                "https://images.unsplash.com/photo-1451187580459-43490279c0fa?w=400", // Space/Data
                "https://images.unsplash.com/photo-1519389950473-47ba0277781c?w=400", // Tech team
                "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=400", // Modern UI
                "https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?w=400", // Development
                "https://images.unsplash.com/photo-1555664424-778a1e5e1b48?w=400"  // Tech lab
            };
            
            for (int i = 0; i < names.length; i++) {
                profiles.add(new StudentProfile("Tech Insight " + (i+1), techUrls[i % techUrls.length]));
            }
        } else if ("Higher Education".equals(category)) {
            description = "Elite Global Universities & Learning";
            String[] eduUrls = {
                "https://images.unsplash.com/photo-1541339907198-e08756ebafe3?w=400", // University
                "https://images.unsplash.com/photo-1523050854058-8df90110c9f1?w=400", // Graduation
                "https://images.unsplash.com/photo-1498243639351-a6c9f99a3c0a?w=400", // Library
                "https://images.unsplash.com/photo-1503676260728-1c00da094a0b?w=400", // International students
                "https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=400", // Study group
                "https://images.unsplash.com/photo-1524178232363-1fb2b075b655?w=400", // Lecture hall
                "https://images.unsplash.com/photo-1513542789411-b6a5d4f31634?w=400", // Creative study
                "https://images.unsplash.com/photo-1523240715639-99f840e5362e?w=400", // Students smiling
                "https://images.unsplash.com/photo-1521737604893-d14cc237f11d?w=400", // Modern office
                "https://images.unsplash.com/photo-1427504494785-3a9ca7044f45?w=400", // School
                "https://images.unsplash.com/photo-1517694712202-14dd9538aa97?w=400", // MacBook study
                "https://images.unsplash.com/photo-1501504905252-473c47e087f8?w=400"  // Courses
            };
            for (int i = 0; i < names.length; i++) {
                profiles.add(new StudentProfile("Campus Life " + (i+1), eduUrls[i % eduUrls.length]));
            }
        } else {
            description = "Elite Career Exploration";
            for (int i = 0; i < names.length; i++) {
                profiles.add(new StudentProfile(names[i], "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=400&sig=" + i));
            }
        }

        tvDescription.setText(description);
        
        videoView.setVideoURI(Uri.parse(videoUrl));
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        videoView.setMediaController(mc);

        btnPlay.setOnClickListener(v -> {
            ivPlaceholder.setVisibility(View.GONE);
            btnPlay.setVisibility(View.GONE);
            videoView.start();
        });

        rvImages.setLayoutManager(new GridLayoutManager(this, 3));
        rvImages.setAdapter(new GalleryAdapter(profiles));
    }

    private static class StudentProfile {
        String name;
        Object imageSource;
        StudentProfile(String name, Object imageSource) {
            this.name = name;
            this.imageSource = imageSource;
        }
    }

    private static class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
        List<StudentProfile> profiles;
        GalleryAdapter(List<StudentProfile> profiles) { this.profiles = profiles; }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery_image, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            StudentProfile profile = profiles.get(position);
            
            Glide.with(holder.img.getContext())
                .load(profile.imageSource)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .placeholder(R.drawable.header_gradient)
                .into(holder.img);
            
            holder.name.setText(profile.name);
        }

        @Override
        public int getItemCount() { return profiles.size(); }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView name;
            ViewHolder(View v) { 
                super(v); 
                img = v.findViewById(R.id.ivGalleryDetailItem); 
                name = v.findViewById(R.id.tvStudentName);
            }
        }
    }
}
