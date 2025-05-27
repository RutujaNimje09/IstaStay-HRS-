package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ConfirmationActivity extends AppCompatActivity {
    private static final String TAG = "ConfirmationActivity";

    private EditText fullName, phoneNumber;
    private Button btnPay;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);

        fullName = findViewById(R.id.full_name);
        phoneNumber = findViewById(R.id.phone_number);
        btnPay = findViewById(R.id.btnPay);
        btnPay.setEnabled(false);

        dbHelper = new DatabaseHelper(ConfirmationActivity.this);

        TextWatcher textWatcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInput();
            }
            @Override public void afterTextChanged(Editable s) {}
        };

        fullName.addTextChangedListener(textWatcher);
        phoneNumber.addTextChangedListener(textWatcher);

        Intent intent = getIntent();
        String hotelName = intent.getStringExtra("hotel_name");
        String hotelLocation = intent.getStringExtra("hotel_location");
        int hotelImage = intent.getIntExtra("hotel_image", R.drawable.room6);
        String checkInDate = intent.getStringExtra("check_in");
        String checkOutDate = intent.getStringExtra("check_out");
        String numGuests = intent.getStringExtra("num_guests");

        int pricePerNight = intent.getIntExtra("price", 1000);
        int serviceTax = intent.getIntExtra("service_tax", 25);
        int extraChargePerDay = intent.getIntExtra("extra_charge", 50);

        int hotelId = intent.getIntExtra("hotel_id", -1);
        int roomNumber = intent.getIntExtra("room_number", -1);

        if (numGuests == null || numGuests.isEmpty()) {
            Log.e(TAG, "Received numGuests is NULL or empty!");
            numGuests = "Not Provided";
        }

        int numNights = calculateDays(checkInDate, checkOutDate);
        int totalRoomPrice = pricePerNight * numNights;
        int totalExtraCharges = extraChargePerDay * numNights;
        int totalPayable = totalRoomPrice + totalExtraCharges + serviceTax;

        Log.d(TAG, "Total Nights: " + numNights + ", Total Payable: ₹" + totalPayable);
        Log.d(TAG, "Hotel ID: " + hotelId + ", Room Number: " + roomNumber);

        ((TextView) findViewById(R.id.confirmHotelName)).setText(hotelName);
        ((TextView) findViewById(R.id.confirmHotelLocation)).setText(hotelLocation);
        ((ImageView) findViewById(R.id.confirmHotelImage)).setImageResource(hotelImage);
        ((TextView) findViewById(R.id.confirmCheckIn)).setText("Check-in: " + checkInDate);
        ((TextView) findViewById(R.id.confirmCheckOut)).setText("Check-out: " + checkOutDate);
        ((TextView) findViewById(R.id.confirmTotalNights)).setText("Total Nights: " + numNights);
        ((TextView) findViewById(R.id.billRoomCharge)).setText("Room Charge: ₹" + totalRoomPrice);
        ((TextView) findViewById(R.id.billExtraCharge)).setText("Extra Charges: ₹" + totalExtraCharges);
        ((TextView) findViewById(R.id.billServiceTax)).setText("Service Tax: ₹" + serviceTax);
        ((TextView) findViewById(R.id.confirmTotalPrice)).setText("Total Payable: ₹" + totalPayable);

        btnPay.setOnClickListener(v -> {
            if (isValidPhoneNumber(phoneNumber.getText().toString().trim())) {
                // No need to check room availability or insert booking again
                Intent payIntent = new Intent(ConfirmationActivity.this, com.example.miniproject.ReviewPay.class);
                payIntent.putExtra("hotel_name", hotelName);
                payIntent.putExtra("hotel_location", hotelLocation);
                payIntent.putExtra("price", pricePerNight);
                payIntent.putExtra("hotel_image", hotelImage);
                startActivity(payIntent);
            } else {
                Toast.makeText(ConfirmationActivity.this, "Please enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateInput() {
        String name = fullName.getText().toString().trim();
        String phone = phoneNumber.getText().toString().trim();
        btnPay.setEnabled(!name.isEmpty() && isValidPhoneNumber(phone));
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    private int calculateDays(String checkIn, String checkOut) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date dateIn = sdf.parse(checkIn);
            Date dateOut = sdf.parse(checkOut);
            if (dateIn == null || dateOut == null || dateOut.before(dateIn)) {
                return 1;
            }
            long diff = dateOut.getTime() - dateIn.getTime();
            return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
