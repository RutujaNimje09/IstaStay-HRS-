package com.example.miniproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etMobile, etPassword, etConfirmPassword;
    private Button btnRegisterFinal;
    private TextView loginRedirect;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegisterFinal = findViewById(R.id.btnRegisterFinal);
        loginRedirect = findViewById(R.id.loginRedirect);

        // ✅ Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        Log.d("RegisterActivity", "Database Opened Successfully");

        btnRegisterFinal.setEnabled(true);
        btnRegisterFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RegisterActivity", "Register Button Clicked");
                registerUser();
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // ✅ Validate Inputs
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(mobile) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mobile.length() != 10) {
            Toast.makeText(this, "Enter a valid 10-digit mobile number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // ✅ Check if email already exists
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
            cursor.close();
            return;
        }
        cursor.close();

        // ✅ Insert user into database
        try {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("email", email);
            values.put("mobile", mobile);
            values.put("password", password); // ⚠️ Consider hashing the password

            long result = db.insert("users", null, values);
            if (result != -1) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                Log.d("RegisterActivity", "User registered successfully with ID: " + result);
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Error: Registration failed", Toast.LENGTH_SHORT).show();
                Log.e("RegisterActivity", "User registration failed, insert returned -1");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Database Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("RegisterActivity", "Database Error: " + e.getMessage());
        }
    }
}
