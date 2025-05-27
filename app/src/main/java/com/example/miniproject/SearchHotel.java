package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SearchHotel extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchhotel);

        // Initializing buttons
        b1 = findViewById(R.id.showdtlsrb);
        b2 = findViewById(R.id.showdtlsti);
        b3 = findViewById(R.id.showdtlscp);
        b4 = findViewById(R.id.showdtlshh);
        b5 = findViewById(R.id.showdtlstulii);
        b6 = findViewById(R.id.btnLuxury);
        b7 = findViewById(R.id.btnBudget);
        b8 = findViewById(R.id.btnBoutique);
        b9 = findViewById(R.id.btnHostel);
        b10 = findViewById(R.id.btnResort);


        // Setting click listeners
        b1.setOnClickListener(v -> openMainActivity());
        b2.setOnClickListener(v -> openMainActivity());
        b3.setOnClickListener(v -> openMainActivity());
        b4.setOnClickListener(v -> openMainActivity());
        b5.setOnClickListener(v -> openMainActivity());
        b6.setOnClickListener(v -> openMainActivity());
        b7.setOnClickListener(v -> openMainActivity());
        b8.setOnClickListener(v -> openMainActivity());
        b9.setOnClickListener(v -> openMainActivity());
        b10.setOnClickListener(v -> openMainActivity());


    }

    private void openMainActivity() {
        Intent intent = new Intent(SearchHotel.this, MainActivity.class);
        startActivity(intent);
    }
}
