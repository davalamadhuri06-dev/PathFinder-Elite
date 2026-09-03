package com.example.pathfinder2;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MentorshipExpertActivity extends AppCompatActivity {

    LinearLayout layoutMessages;
    EditText etMessage;
    ImageButton btnSend;
    String careerName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_chat);

        careerName = getIntent().getStringExtra("CAREER_NAME");
        TextView tvTitle = findViewById(R.id.tvChatTitle);
        if (tvTitle != null) tvTitle.setText("Expert " + careerName + " Mentor");

        layoutMessages = findViewById(R.id.layoutMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSendMessage);

        addMentorMessage("Welcome to your Premium Mentorship! I am your dedicated " + careerName + " specialist. How can I guide your journey today?");

        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();
            if (!msg.isEmpty()) {
                addUserMessage(msg);
                etMessage.setText("");
                simulateMentorResponse(msg);
            }
        });
    }

    private void addUserMessage(String msg) {
        TextView tv = new TextView(this);
        tv.setText(msg);
        tv.setBackgroundResource(R.drawable.premium_card_bg);
        tv.setPadding(32, 24, 32, 24);
        tv.setTextColor(ContextCompat.getColor(this, R.color.white));
        tv.setBackgroundTintList(android.content.res.ColorStateList.valueOf(ContextCompat.getColor(this, R.color.primary_accent)));
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.END;
        params.setMargins(100, 16, 0, 16);
        tv.setLayoutParams(params);
        layoutMessages.addView(tv);
    }

    private void addMentorMessage(String msg) {
        TextView tv = new TextView(this);
        tv.setText(msg);
        tv.setBackgroundResource(R.drawable.premium_card_bg);
        tv.setPadding(32, 24, 32, 24);
        tv.setTextColor(ContextCompat.getColor(this, R.color.text_title));
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.START;
        params.setMargins(0, 16, 100, 16);
        tv.setLayoutParams(params);
        layoutMessages.addView(tv);
    }

    private void simulateMentorResponse(String input) {
        new Handler().postDelayed(() -> {
            String resp = "As an expert in " + careerName + ", I've noted your query about '" + input + "'. Let's schedule a deep-dive call this weekend to discuss your specific roadmap and industry secrets. In the meantime, check the Case Studies section!";
            addMentorMessage(resp);
        }, 1500);
    }
}
