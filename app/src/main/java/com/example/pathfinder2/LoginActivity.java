package com.example.pathfinder2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvToRegister, tvTitle;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvToRegister = findViewById(R.id.tvToRegister);
        tvTitle = findViewById(R.id.tvTitle);

        // Secret: Long-click title to access Admin Panel
        tvTitle.setOnLongClickListener(v -> {
            showAdminLoginDialog();
            return true;
        });

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
            } else if (email.equalsIgnoreCase("admin@pathfinder.com") && password.equals("admin123")) {
                // Secret Admin Entry
                Toast.makeText(this, "Master Admin Authorized", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ViewResultsActivity.class));
            } else if (db.checkUser(email, password)) {
                db.updateLastLogin(email);
                String userName = db.getUserName(email);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("USER_NAME", userName);
                intent.putExtra("USER_EMAIL", email);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            }
        });

        tvToRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });
    }

    private void showAdminLoginDialog() {
        android.view.View dialogView = getLayoutInflater().inflate(R.layout.dialog_admin_login, null);
        EditText etPin = dialogView.findViewById(R.id.etAdminPin);
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("UNLOCK", (d, w) -> {
                    String password = etPin.getText().toString().trim();
                    if (password.equalsIgnoreCase("admin123")) {
                        startActivity(new Intent(LoginActivity.this, ViewResultsActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }
}
