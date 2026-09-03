package com.example.pathfinder2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etAge, etPhone, etLocation, etDream;
    CheckBox cbTerms;
    Button btnRegister;
    TextView tvToLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        etName = findViewById(R.id.etRegName);
        etEmail = findViewById(R.id.etRegEmail);
        etPassword = findViewById(R.id.etRegPassword);
        etAge = findViewById(R.id.etRegAge);
        etPhone = findViewById(R.id.etRegPhone);
        etLocation = findViewById(R.id.etRegLocation);
        etDream = findViewById(R.id.etRegDream);
        cbTerms = findViewById(R.id.cbTerms);
        btnRegister = findViewById(R.id.btnRegister);
        tvToLogin = findViewById(R.id.tvToLogin);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String location = etLocation.getText().toString().trim();
            String dream = etDream.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || ageStr.isEmpty() || phone.isEmpty() || location.isEmpty() || dream.isEmpty()) {
                Toast.makeText(this, "Please fulfill all elite fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!cbTerms.isChecked()) {
                Toast.makeText(this, "Please accept the privacy terms to proceed", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);
            if (age < 15) {
                Toast.makeText(this, "Access Denied: Elite PathFinder requires age 15+", Toast.LENGTH_LONG).show();
                return;
            }

            boolean success = db.registerUser(name, email, password, age, phone, location, dream);
            if (success) {
                syncToCloud(name, email, age, phone, location, dream); 
                Toast.makeText(this, "Welcome to the Elite Network!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

        tvToLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void syncToCloud(String name, String email, int age, String phone, String location, String dream) {
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("GlobalUsers");
            
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("name", name);
            userMap.put("email", email);
            userMap.put("age", age);
            userMap.put("phone", phone);
            userMap.put("location", location);
            userMap.put("dream", dream);
            userMap.put("regDate", new Date().toString());
            
            ref.push().setValue(userMap);
            Log.d("CLOUD_SYNC", "User " + name + " synced to Master Cloud List.");
        } catch (Exception e) {
            Log.e("CLOUD_SYNC", "Firebase connection required for Cloud Sync.");
        }
    }
}
