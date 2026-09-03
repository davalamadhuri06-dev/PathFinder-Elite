package com.example.pathfinder2;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.Random;

public class AiChatActivity extends AppCompatActivity {

    LinearLayout layoutMessages;
    EditText etMessage;
    ImageButton btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_chat);

        layoutMessages = findViewById(R.id.layoutMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSendMessage);

        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();
            if (!msg.isEmpty()) {
                addUserMessage(msg);
                etMessage.setText("");
                generateAiResponse(msg.toLowerCase());
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
        params.setMargins(100, 0, 0, 24);
        tv.setLayoutParams(params);
        
        layoutMessages.addView(tv);
    }

    private void addAiMessage(String msg) {
        TextView tv = new TextView(this);
        tv.setText(msg);
        tv.setBackgroundResource(R.drawable.premium_card_bg);
        tv.setPadding(32, 24, 32, 24);
        tv.setTextColor(ContextCompat.getColor(this, R.color.text_title));
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.START;
        params.setMargins(0, 0, 100, 24);
        tv.setLayoutParams(params);
        
        layoutMessages.addView(tv);
    }

    private void generateAiResponse(String input) {
        String response;
        Random random = new Random();

        // 1. GREETINGS & PERSONALITY
        if (input.contains("hello") || input.contains("hi") || input.contains("hey")) {
            String[] greets = {
                "Hello! I'm your PathFinder AI. I've analyzed thousands of career paths—where shall we start?",
                "Hi there! I'm ready to help you build your future. What's on your mind?",
                "Greetings, Explorer! Are you looking for a stream suggestion or specific career advice?"
            };
            response = greets[random.nextInt(greets.length)];
        } 
        
        // 2. INTEREST-BASED LOGIC (Listening to what they like)
        else if (input.contains("math") || input.contains("calculation") || input.contains("numbers")) {
            response = "Since you enjoy Math, you'd excel in Data Science, Architecture, or Engineering. Are you in 10th or 12th right now?";
        }
        else if (input.contains("science") || input.contains("physics") || input.contains("chemistry")) {
            response = "Science is a powerful choice! It opens doors to Research, Tech, and Space Science. Do you prefer Engineering (PCM) or Medical (PCB)?";
        }
        else if (input.contains("art") || input.contains("design") || input.contains("drawing") || input.contains("creative")) {
            response = "Creative minds are in high demand! Have you considered UI/UX Design, Fashion, or Animation? These are high-paying modern careers.";
        }
        else if (input.contains("people") || input.contains("social") || input.contains("help") || input.contains("teaching")) {
            response = "If you enjoy working with people, careers in Psychology, Human Resources, or International Relations might be your true calling.";
        }

        // 3. GRADE-SPECIFIC ADVICE
        else if (input.contains("10th")) {
            String[] answers = {
                "After 10th, the Choice is critical. Science (PCM/PCB), Commerce, or Arts? Tell me your favorite subject, and I'll suggest a stream.",
                "10th grade is the foundation. If you want to be a Doctor/Engineer, choose Science. For Business/Finance, choose Commerce.",
                "Most elite students after 10th choose a stream that matches their hobby. What do you do in your free time?"
            };
            response = answers[random.nextInt(answers.length)];
        } 
        else if (input.contains("12th")) {
            String[] answers = {
                "After 12th, the focus shifts to entrance exams. Are you preparing for JEE, NEET, CLAT, or IPMAT?",
                "12th is done! Now you choose a specialization like B.Tech, MBBS, BBA, or Liberal Arts. What is your dream job title?",
                "The next 4 years will define your career. I recommend checking our 'Elite Funding' section if you're worried about college fees."
            };
            response = answers[random.nextInt(answers.length)];
        } 

        // 4. CAREER PATHS
        else if (input.contains("coding") || input.contains("software") || input.contains("computer") || input.contains("ai") || input.contains("tech")) {
            response = "The Tech world moves fast! Learn Python first, then move to Data Structures. A B.Tech in Computer Science is the gold standard.";
        } 
        else if (input.contains("doctor") || input.contains("medical") || input.contains("nurse") || input.contains("biology")) {
            response = "Medicine is a noble path. You'll need to clear NEET. If not MBBS, look into Biotechnology or Genetics—they are the future of healthcare!";
        } 
        else if (input.contains("law") || input.contains("legal") || input.contains("court") || input.contains("clat")) {
            response = "Law is excellent for sharp minds. Clear the CLAT exam to enter a National Law University (NLU). Corporate Law is very lucrative!";
        }
        else if (input.contains("business") || input.contains("mba") || input.contains("management") || input.contains("ceo")) {
            response = "To become a CEO or Manager, start with a BBA or B.Com, then aim for an MBA from an IIM. It's all about leadership and strategy!";
        }

        // 5. SUCCESS & MONEY
        else if (input.contains("salary") || input.contains("money") || input.contains("rich") || input.contains("earn")) {
            response = "Wealth comes from specialized skills. Currently, AI Engineers, Surgeons, and Investment Bankers are the highest earners globally.";
        } 
        else if (input.contains("how") || input.contains("guide") || input.contains("steps") || input.contains("process")) {
            response = "To give you a step-by-step guide, I need to know your goal. For example, ask 'How to become a Pilot?' or 'How to get into IIT?'";
        }

        // 6. CLOSURE
        else if (input.contains("thank") || input.contains("thanks") || input.contains("ok") || input.contains("clear")) {
            response = "Glad I could help! Remember, the best career is where your talent meets the world's needs. Anything else?";
        } 
        
        // 7. SMART DEFAULT
        else {
            response = "That's a specific query! Try asking about 'streams after 10th', 'high paying jobs', or 'how to become a [career]'. I'm here to help!";
        }
        
        // Simulate thinking delay
        layoutMessages.postDelayed(() -> addAiMessage(response), 800);
    }
}
