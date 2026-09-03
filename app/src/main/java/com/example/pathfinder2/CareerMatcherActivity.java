package com.example.pathfinder2;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CareerMatcherActivity extends AppCompatActivity {

    CardView cardInterest;
    TextView tvEmoji, tvTitle, tvDesc;
    FloatingActionButton btnYes, btnNo;
    int currentIndex = 0;

    String[] emojis = {"🚀", "💻", "⚖️", "🏥", "🎨", "📊"};
    String[] titles = {"Space & Research", "Software Engineering", "Law & Justice", "Medical Science", "Design & Arts", "Business & Finance"};
    String[] descs = {
            "Build the next generation of space travel.",
            "Create apps and AI that change the world.",
            "Fight for the truth in the courtroom.",
            "Save lives and advance healthcare.",
            "Express your vision through digital art.",
            "Master the markets and lead big companies."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_matcher);

        cardInterest = findViewById(R.id.cardInterest);
        tvEmoji = findViewById(R.id.tvInterestEmoji);
        tvTitle = findViewById(R.id.tvInterestTitle);
        tvDesc = findViewById(R.id.tvInterestDesc);
        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);

        btnYes.setOnClickListener(v -> nextCard(true));
        btnNo.setOnClickListener(v -> nextCard(false));

        updateCard();
    }

    private void nextCard(boolean interested) {
        if (interested) {
            // Higher score for "Yes"
            currentIndex++; 
        } else {
            currentIndex++;
        }

        Animation anim = AnimationUtils.loadAnimation(this, interested ? R.anim.slide_out_right : R.anim.slide_out_left);
        cardInterest.startAnimation(anim);
        
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation) {
                if (currentIndex < titles.length) {
                    updateCard();
                    cardInterest.startAnimation(AnimationUtils.loadAnimation(CareerMatcherActivity.this, R.anim.fade_in_slide_up));
                } else {
                    showFinalMatch();
                }
            }
        });
    }

    private void showFinalMatch() {
        // Creative "Scanning" result
        setContentView(R.layout.activity_match_result);
        TextView tvResTitle = findViewById(R.id.tvMatchResultTitle);
        TextView tvScore = findViewById(R.id.tvMatchScore);
        
        tvResTitle.setText("Top Talent Match: " + titles[0]); // Simplification for demo
        tvScore.setText("Compatibility: 98%");
        
        findViewById(R.id.btnClaimCareer).setOnClickListener(v -> {
            Toast.makeText(this, "Path Locked! Check your Elite Portfolio.", Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private void updateCard() {
        tvEmoji.setText(emojis[currentIndex]);
        tvTitle.setText(titles[currentIndex]);
        tvDesc.setText(descs[currentIndex]);
    }
}
