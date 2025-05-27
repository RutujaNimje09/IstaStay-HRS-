package com.example.miniproject;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReviewPay extends AppCompatActivity {
    private static final String CHANNEL_ID = "booking_channel";
    private RadioGroup paymentGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        createNotificationChannel();

        paymentGroup = findViewById(R.id.paymentGroup);
        Button btnConfirm = findViewById(R.id.btnContinue);

        btnConfirm.setOnClickListener(v -> {
            int selectedId = paymentGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(ReviewPay.this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            } else {
                showConfirmationDialog(); // Call the confirmation dialog
            }
        });
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Booking")
                .setMessage("Are you sure you want to confirm your booking?")
                .setPositiveButton("Yes", (dialog, which) -> checkAndSendNotification())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void checkAndSendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
                return;
            }
        }

        try {
            sendBookingNotification();
            Toast.makeText(ReviewPay.this, "🎉 Your room is confirmed!", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Log.e("NotificationError", "Notification permission not granted", e);
            Toast.makeText(this, "Cannot send notification. Permission denied!", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void sendBookingNotification() {
        createNotificationChannel();

        // Replace BookingDetailsActivity.class with your actual details activity if available
        Intent detailsIntent = new Intent(this, Activity2.class);
        PendingIntent detailsPendingIntent = PendingIntent.getActivity(
                this, 0, detailsIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Booking Confirmed ✅")
                .setContentText("Your room has been confirmed.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(detailsPendingIntent)
                .addAction(0, "Show Details", detailsPendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show();

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                    sendBookingNotification(); // Safe to call now
                }

            } else {
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Booking Confirmation";
            String description = "Notifies when a booking is confirmed.";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
