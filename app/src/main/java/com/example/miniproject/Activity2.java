package com.example.miniproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class Activity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        TextView roomDetails = findViewById(R.id.room_details);
        Button btnCallHotel = findViewById(R.id.btnCallHotel);
        Button btnCancelBooking = findViewById(R.id.btnCancelBooking);

        // Generate a unique Room ID
        String roomId = "RC" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        roomDetails.setText("Room ID: " + roomId);

        btnCallHotel.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:+1234567890"));
            startActivity(callIntent);
        });

        btnCancelBooking.setOnClickListener(v -> finish());
    }
}