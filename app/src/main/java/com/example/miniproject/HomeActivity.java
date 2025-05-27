package com.example.miniproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    private EditText location, checkinDate, checkoutDate, guests, rooms;
    private CheckBox business, family, romantic;
    private Button searchButton;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        location = findViewById(R.id.location);
        checkinDate = findViewById(R.id.checkin_date);
        checkoutDate = findViewById(R.id.checkout_date);
        guests = findViewById(R.id.guests);
        rooms = findViewById(R.id.rooms);
        business = findViewById(R.id.business);
        family = findViewById(R.id.family);
        romantic = findViewById(R.id.romantic);
        searchButton = findViewById(R.id.search_button);

        calendar = Calendar.getInstance();

        // Open Date Picker for Check-in Date
        checkinDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(checkinDate);
            }
        });

        // Open Date Picker for Check-out Date
        checkoutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(checkoutDate);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,SearchHotel.class);
                startActivity(intent);
            }
        });
    }

    private void showDatePicker(final EditText dateField) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1; // Month is 0-based, so add 1
                String selectedDate = dayOfMonth + "/" + month + "/" + year;
                dateField.setText(selectedDate);
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    private void performSearch() {
        String loc = location.getText().toString().trim();
        String checkin = checkinDate.getText().toString().trim();
        String checkout = checkoutDate.getText().toString().trim();
        String guestCount = guests.getText().toString().trim();
        String roomCount = rooms.getText().toString().trim();
        String tripType = "";

        if (business.isChecked()) {
            tripType += "Business ";
        }
        if (family.isChecked()) {
            tripType += "Family ";
        }
        if (romantic.isChecked()) {
            tripType += "Romantic ";
        }

        if (loc.isEmpty() || checkin.isEmpty() || checkout.isEmpty() || guestCount.isEmpty() || roomCount.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
