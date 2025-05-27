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
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {

    private EditText etEmail, etNewPassword, etConfirmPassword;
    private Button btnVerifyEmail, btnResetPassword;
    private SQLiteDatabase db;
    private boolean isEmailVerified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        etEmail = findViewById(R.id.etEmail);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmNewPassword);
        btnVerifyEmail = findViewById(R.id.btnVerifyEmail);
        btnResetPassword = findViewById(R.id.btnResetPassword);


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        //hide
        etNewPassword.setVisibility(View.GONE);
        etConfirmPassword.setVisibility(View.GONE);
        btnResetPassword.setVisibility(View.GONE);

        // Verify email
        btnVerifyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyEmail();
            }
        });

        // Reset password
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmailVerified) {
                    resetPassword();
                } else {
                    Toast.makeText(ForgotPassword.this, "Please verify email first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verifyEmail() {
        String email = etEmail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check email exists in database
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=?", new String[]{email});
        if (cursor.moveToFirst()) {
            isEmailVerified = true; // Email exists, mark as verified
            Toast.makeText(this, "Email verified! You can reset your password.", Toast.LENGTH_SHORT).show();

            // Show password fields and reset button
            etNewPassword.setVisibility(View.VISIBLE);
            etConfirmPassword.setVisibility(View.VISIBLE);
            btnResetPassword.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Email not registered", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    private void resetPassword() {
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update password in the database
        ContentValues values = new ContentValues();
        values.put("password", newPassword);  // ❗ Storing plain text passwords is NOT SECURE!

        int rowsAffected = db.update("users", values, "email=?", new String[]{email});
        if (rowsAffected > 0) {
            Toast.makeText(this, "Password reset successful!", Toast.LENGTH_SHORT).show();

            // Open LoginActivity after successful reset
            Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Password reset failed", Toast.LENGTH_SHORT).show();
        }
    }
}
