package com.example.pathfinder2;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewResultsActivity extends AppCompatActivity {

    DatabaseHelper db;
    TextView tvDisplay;
    EditText etApproveId, etDeleteEmail;
    Button btnApprove, btnDelete;
    private StringBuilder localBuffer = new StringBuilder();
    private StringBuilder globalBuffer = new StringBuilder();
    private List<String> globalKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);

        db = new DatabaseHelper(this);
        tvDisplay = findViewById(R.id.tvDisplayData);
        etApproveId = findViewById(R.id.etApproveId);
        etDeleteEmail = findViewById(R.id.etDeleteEmail);
        btnApprove = findViewById(R.id.btnApproveMentor);
        btnDelete = findViewById(R.id.btnRemoveStudent);

        btnApprove.setOnClickListener(v -> {
            String id = etApproveId.getText().toString().trim();
            if (id.isEmpty()) {
                Toast.makeText(this, "Enter Application ID", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean success = db.updateApplicationStatus(id, "✅ APPROVED");
            if (success) {
                Toast.makeText(this, "Mentorship Approved for ID: " + id, Toast.LENGTH_SHORT).show();
                refreshUI(); 
            } else {
                Toast.makeText(this, "ID not found!", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(v -> {
            String input = etDeleteEmail.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(this, "Enter Email or ID", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if it's an application ID and if it's approved
            String appStatus = db.getApplicationStatus(input);
            boolean deletedApp = false;
            if (!appStatus.isEmpty()) {
                if (appStatus.contains("APPROVED")) {
                    deletedApp = db.deleteApplication(input);
                } else {
                    Toast.makeText(this, "Mentorship not approved. Ignoring removal.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            boolean deletedLocal = db.deleteUser(input);
            boolean deletedGlobal = false;

            try {
                // Check if input is a numeric Global ID
                int globalIndex = Integer.parseInt(input);
                if (globalIndex > 0 && globalIndex <= globalKeys.size()) {
                    String firebaseKey = globalKeys.get(globalIndex - 1);
                    FirebaseDatabase.getInstance().getReference("GlobalUsers").child(firebaseKey).removeValue();
                    deletedGlobal = true;
                }
            } catch (Exception e) {}

            if (deletedLocal || deletedApp || deletedGlobal) {
                Toast.makeText(this, "Record successfully purged", Toast.LENGTH_SHORT).show();
                refreshUI();
            } else {
                Toast.makeText(this, "Record not found!", Toast.LENGTH_SHORT).show();
            }
        });


        refreshUI();
        fetchGlobalUsers();
    }

    private void refreshUI() {
        Cursor appsRes = db.getAllApplications();
        Cursor feedRes = db.getAllFeedback();
        
        localBuffer = new StringBuilder();
        localBuffer.append("👑 MASTER CONTROL PANEL\n");
        localBuffer.append("━━━━━━━━━━━━━━━━━━━━\n\n");

        localBuffer.append("💎 COURSE APPLICATIONS\n");
        localBuffer.append("━━━━━━━━━━━━━━━━━━━━\n");
        if (appsRes.getCount() == 0) {
            localBuffer.append("No applications yet.\n\n");
        } else {
            while (appsRes.moveToNext()) {
                localBuffer.append("🆔 ID: ").append(appsRes.getString(0)).append("\n");
                localBuffer.append("👤 Student: ").append(appsRes.getString(1)).append("\n");
                localBuffer.append("📚 Course: ").append(appsRes.getString(2)).append("\n");
                localBuffer.append("🛡️ Status: ").append(appsRes.getString(3)).append("\n");
                localBuffer.append("━━━━━━━━━━━━━━━━━━━━\n");
            }
            localBuffer.append("\n");
        }

        localBuffer.append("🌟 APP FEEDBACK (REVIEWS)\n");
        localBuffer.append("━━━━━━━━━━━━━━━━━━━━\n");
        if (feedRes.getCount() == 0) {
            localBuffer.append("No feedback received yet.\n");
        } else {
            while (feedRes.moveToNext()) {
                localBuffer.append("📧 From: ").append(feedRes.getString(1)).append("\n");
                localBuffer.append("👍 Useful: ").append(feedRes.getString(2)).append("\n");
                localBuffer.append("⭐ Fav Feature: ").append(feedRes.getString(3)).append("\n");
                localBuffer.append("💬 Suggestions: ").append(feedRes.getString(4)).append("\n");
                localBuffer.append("━━━━━━━━━━━━━━━━━━━━\n\n");
            }
        }
        updateDisplay();
        appsRes.close();
        feedRes.close();
    }

    private void updateDisplay() {
        tvDisplay.setText(globalBuffer.toString() + localBuffer.toString());
    }

    private void fetchGlobalUsers() {
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("GlobalUsers");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    globalBuffer = new StringBuilder();
                    globalBuffer.append("🌍 GLOBAL REGISTRATIONS (ALL PHONES)\n");
                    globalBuffer.append("━━━━━━━━━━━━━━━━━━━━\n");
                    globalKeys.clear();
                    
                    if (snapshot.exists()) {
                        int index = 1;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            globalKeys.add(ds.getKey());
                            String name = ds.child("name").getValue(String.class);
                            String email = ds.child("email").getValue(String.class);
                            Object ageObj = ds.child("age").getValue();
                            String phone = ds.child("phone").getValue(String.class);
                            String location = ds.child("location").getValue(String.class);
                            String dream = ds.child("dream").getValue(String.class);
                            String date = ds.child("regDate").getValue(String.class);
                            
                            int ageInt = 0;
                            try {
                                if (ageObj instanceof Long) ageInt = ((Long) ageObj).intValue();
                                else if (ageObj instanceof Integer) ageInt = (Integer) ageObj;
                                else if (ageObj instanceof String) ageInt = Integer.parseInt((String) ageObj);
                            } catch (Exception e) {}

                            globalBuffer.append("🌍 GLOBAL ID: ").append(index++).append("\n");
                            globalBuffer.append("👤 Name: ").append(name).append("\n");
                            globalBuffer.append("📧 Email: ").append(email).append("\n");
                            globalBuffer.append("🌟 Dream: ").append(dream != null ? dream : "N/A").append("\n");
                            globalBuffer.append("🎂 Age: ").append(ageObj != null ? ageObj : "N/A");
                            if (ageInt > 0 && ageInt < 15) {
                                globalBuffer.append(" ⚠️ INELIGIBLE (Under 15)");
                            }
                            globalBuffer.append("\n");
                            globalBuffer.append("📞 Phone: ").append(phone != null ? phone : "N/A").append("\n");
                            globalBuffer.append("📍 Location: ").append(location != null ? location : "N/A").append("\n");
                            globalBuffer.append("📅 Date: ").append(date != null ? date : "").append("\n");
                            globalBuffer.append("━━━━━━━━━━━━━━━━━━━━\n");
                        }
                        globalBuffer.append("\n");
                    } else {
                        globalBuffer.append("No cloud data yet.\n\n");
                    }
                    updateDisplay();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ADMIN_CLOUD", "Error: " + error.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e("ADMIN_CLOUD", "Firebase connection required for Global Data.");
        }
    }
}
