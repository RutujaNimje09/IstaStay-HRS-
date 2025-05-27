package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class Detail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        // Get data from intent
        Intent intent = getIntent();
        String hotelName = intent.getStringExtra("hotel_name");
        String hotelLocation = intent.getStringExtra("hotel_location");
        String hotelPrice = intent.getStringExtra("hotel_price");
        int price = intent.getIntExtra("price",1000);
        int hotelImage = intent.getIntExtra("hotel_image", R.drawable.room5); // Default to 0 if no image is passed

        // Set data to UI elements
        TextView nameTextView = findViewById(R.id.hotelName);
        TextView locationTextView = findViewById(R.id.hotelLocation);
        TextView priceTextView = findViewById(R.id.hotelPrice);
        ImageView hotelImageView = findViewById(R.id.hotelImage);
        WebView webView = findViewById(R.id.webView);
        Button bookButton = findViewById(R.id.book);

        nameTextView.setText(hotelName);
        locationTextView.setText(hotelLocation);
        priceTextView.setText(hotelPrice);
        hotelImageView.setImageResource(hotelImage);

        TextView hotelDesc = findViewById(R.id.hotelDescription);
        String description = getIntent().getStringExtra("hotel_description");

        if (description != null) {
            hotelDesc.setText(description);
        } else {
            hotelDesc.setText("Experience a luxurious stay at our hotel.");
        }

        String hotelHtml = getIntent().getStringExtra("hotel_html");

        if (hotelHtml != null) {
            webView.loadData(hotelHtml, "text/html", "UTF-8");
        } else {
            webView.loadData("<h2>Welcome to Our Hotel</h2><p>Enjoy a luxurious stay!</p>", "text/html", "UTF-8");
        }


        Button bookRoom = findViewById(R.id.book);
        bookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail.this, BookingActivity.class);

                // Pass all hotel details dynamically
                intent.putExtra("hotel_name", getIntent().getStringExtra("hotel_name"));
                intent.putExtra("hotel_location", getIntent().getStringExtra("hotel_location"));
                intent.putExtra("hotel_price", getIntent().getStringExtra("hotel_price"));
                intent.putExtra("hotel_image", getIntent().getIntExtra("hotel_image", R.drawable.room6));
                intent.putExtra("price", price); // Default image if none

                startActivity(intent);
            }
        });


    }
}
