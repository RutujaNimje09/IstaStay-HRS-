package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.LinearLayout;

import com.example.miniproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoteldetail);

        LinearLayout sapphLayout = findViewById(R.id.sapphLayout);
        LinearLayout royLayout = findViewById(R.id.royalorchid);
        LinearLayout grandLayout = findViewById(R.id.grandhorizon);
        LinearLayout orangeLayout = findViewById(R.id.orangecity);
        LinearLayout lakeLayout = findViewById(R.id.lakeview);

        sapphLayout.setOnClickListener(hotelClickListener);
        royLayout.setOnClickListener(hotelClickListener);
        grandLayout.setOnClickListener(hotelClickListener);
        orangeLayout.setOnClickListener(hotelClickListener);
        lakeLayout.setOnClickListener(hotelClickListener);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // Set up WebView
        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load Google Maps in WebView
        String mapHtml = "<html><body>" +
                "<iframe width='100%' height='100%' " +
                "src='https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d14881.596245776733!2d79.0508368482367!3d21.176300549180453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1sHotels!5e0!3m2!1sen!2sin!4v1743176766963!5m2!1sen!2sin'>" +
                "</iframe></body></html>";

        webView.loadData(mapHtml, "text/html", "UTF-8");
    }

    // Common click listener for all layouts
    private final View.OnClickListener hotelClickListener = v -> {
        Intent intent = new Intent(MainActivity.this, com.example.miniproject.Detail.class);

        if (v.getId() == R.id.sapphLayout) {
            intent.putExtra("hotel_name", "Hotel Sapphire Bay");
            intent.putExtra("hotel_location", "Wardha Road, Nagpur, Maharashtra 440015");
            intent.putExtra("hotel_price", "₹5,600/night");
            intent.putExtra("hotel_image", R.drawable.room5);
            intent.putExtra("hotel_description", "Experience elegance with a rooftop lounge, spa, and fine dining.");
            intent.putExtra("hotel_html", "<html><body>" +
                    "<h2>🏨 Welcome to Hotel Sapphire Bay</h2>" +
                    "<p>Discover an <b>exquisite blend of luxury and elegance</b> at Hotel Sapphire Bay, " +
                    "Nagpur’s finest retreat for business and leisure travelers.</p>" +

                    "<h3>📍 Nearby Attractions</h3>" +
                    "<ul>" +
                    "<li><b>MIHAN SEZ</b> - 4 km, major IT and business hub.</li>" +
                    "<li><b>Deekshabhoomi</b> - 3 km, a historical Buddhist site.</li>" +
                    "<li><b>Ambazari Lake</b> - 5 km, perfect for relaxation and evening walks.</li>" +
                    "<li><b>Futala Lake</b> - 7 km, famous for its fountains and food stalls.</li>" +
                    "</ul>" +

                    "<h3>🍽️ Dining & Cuisine</h3>" +
                    "<p>Enjoy gourmet dining at our <b>multi-cuisine restaurant</b>, offering Indian, Continental, and Asian delicacies.</p>" +

                    "<h3>🏨 Luxury Amenities</h3>" +
                    "<ul>" +
                    "<li>✅ Rooftop Lounge with City Views</li>" +
                    "<li>✅ Spa & Wellness Center</li>" +
                    "<li>✅ Swimming Pool & Fitness Center</li>" +
                    "<li>✅ Conference Halls & Banquet Facilities</li>" +
                    "</ul>" +

                    "<h3>🛏️ Exclusive Room Features</h3>" +
                    "<p>Experience the finest comfort with <b>premium bedding, private balconies, " +
                    "Jacuzzi suites</b>, and stunning cityscape views.</p>" +

                    "<h3>🌟 Your Luxury Stay Awaits!</h3>" +
                    "<p>Indulge in <b>unmatched hospitality</b> and world-class service at Hotel Sapphire Bay.</p>" +
                    "</body></html>");
            intent.putExtra("price", 5600);
        } else if (v.getId() == R.id.royalorchid) {
            intent.putExtra("hotel_name", "Hotel Royal Orchid");
            intent.putExtra("hotel_location", "Civil Lines, Nagpur, Maharashtra 440001");
            intent.putExtra("hotel_price", "₹4,500/night");
            intent.putExtra("hotel_image", R.drawable.room1);
            intent.putExtra("hotel_description", "Stay in the heart of Nagpur with premium services.");
            intent.putExtra("hotel_html", "<html><body>" +
                    "<h2>🏨 Welcome to Hotel Royal Orchid</h2>" +
                    "<p>Experience refined luxury and convenience at Hotel Royal Orchid, " +
                    "a premium choice for both business and leisure travelers.</p>" +

                    "<h3>📍 Nearby Attractions</h3>" +
                    "<ul>" +
                    "<li><b>Zero Mile Stone</b> - 1.5 km, marking the center of India.</li>" +
                    "<li><b>Telankhedi Garden</b> - 3 km, a scenic spot with lake views.</li>" +
                    "<li><b>Gandhi Sagar Lake</b> - 2.5 km, a beautiful spot for morning walks.</li>" +
                    "<li><b>Raman Science Centre</b> - 2 km, perfect for science enthusiasts.</li>" +
                    "</ul>" +

                    "<h3>🍽️ Fine Dining</h3>" +
                    "<p>Savor exquisite flavors at our <b>award-winning restaurant</b> serving " +
                    "Indian, Mughlai, and International cuisines.</p>" +

                    "<h3>🏨 Top-Class Facilities</h3>" +
                    "<ul>" +
                    "<li>✅ High-Speed Wi-Fi & Business Lounge</li>" +
                    "<li>✅ Fully Equipped Gym</li>" +
                    "<li>✅ Grand Ballroom for Events</li>" +
                    "<li>✅ 24/7 Room Service</li>" +
                    "</ul>" +

                    "<h3>🛏️ Comfortable Stay</h3>" +
                    "<p>Our elegant rooms come with <b>smart TVs, ergonomic work desks, mini-bars</b>, and plush bedding.</p>" +

                    "<h3>🌟 Book Your Stay!</h3>" +
                    "<p>Stay in style at Hotel Royal Orchid and enjoy a premium hospitality experience.</p>" +
                    "</body></html>");
            intent.putExtra("price",4500);
        } else if (v.getId() == R.id.grandhorizon) {
            intent.putExtra("hotel_name", "The Grand Horizon");
            intent.putExtra("hotel_location", "Sadar, Nagpur, Maharashtra 440001");
            intent.putExtra("hotel_price", "₹7,200/night");
            intent.putExtra("hotel_image", R.drawable.rooms2);
            intent.putExtra("hotel_description", "The Grand Horizon is a premium 5-star hotel with spacious suites, a luxurious spa, and an infinity pool.");
            intent.putExtra("hotel_html", "<html><body>" +
                    "<h2>🏨 Welcome to Hotel Blue Diamond</h2>" +
                    "<p>Enjoy a <b>comfortable and affordable stay</b> at Hotel Blue Diamond, " +
                    "conveniently located in the lively Dharampeth area.</p>" +

                    "<h3>📍 Nearby Attractions</h3>" +
                    "<ul>" +
                    "<li><b>Ram Jhula Cable Bridge</b> - 2 km, a beautiful architectural marvel.</li>" +
                    "<li><b>Eternity Mall</b> - 1.5 km, ideal for shopping and entertainment.</li>" +
                    "<li><b>Balaji Temple</b> - 2 km, a peaceful spiritual site.</li>" +
                    "<li><b>Dharampeth Shopping Street</b> - 500m, famous for fashion and jewelry.</li>" +
                    "</ul>" +

                    "<h3>🍽️ Dining Choices</h3>" +
                    "<p>Try local delicacies at nearby food joints or enjoy a meal at our in-house <b>multi-cuisine restaurant</b>.</p>" +

                    "<h3>🏨 Amenities & Services</h3>" +
                    "<ul>" +
                    "<li>✅ Free Wi-Fi</li>" +
                    "<li>✅ 24-Hour Front Desk</li>" +
                    "<li>✅ Laundry & Housekeeping Services</li>" +
                    "<li>✅ Airport & Railway Station Transfers</li>" +
                    "</ul>" +

                    "<h3>🛏️ Cozy Room Features</h3>" +
                    "<p>Each room is equipped with <b>air-conditioning, LCD TVs, study desks</b>, and clean, spacious interiors.</p>" +

                    "<h3>🌟 Stay with Us!</h3>" +
                    "<p>Hotel Blue Diamond offers great value for money with all essential comforts.</p>" +
                    "</body></html>");
            intent.putExtra("price", 7200);
        } else if (v.getId() == R.id.orangecity) {
            intent.putExtra("hotel_name", "Orange City Inn");
            intent.putExtra("hotel_location", "Sitabuldi, Nagpur, Maharashtra 440012");
            intent.putExtra("hotel_price", "₹2,500/night");
            intent.putExtra("hotel_image", R.drawable.room6);
            intent.putExtra("hotel_description", "Budget-friendly comfort in the heart of Nagpur.");
            intent.putExtra("hotel_html", "<html><body>" +
                    "<h2>🏨 Welcome to Orange City Inn</h2>" +
                    "<p>Experience a blend of <b>affordability and comfort</b> at Orange City Inn, " +
                    "located in the bustling commercial hub of <b>Sitabuldi</b>. Whether you're here for business or leisure, " +
                    "our hotel offers a cozy stay with modern amenities.</p>" +

                    "<h3>📍 Nearby Attractions</h3>" +
                    "<ul>" +
                    "<li><b>Sitabuldi Fort</b> - A historic landmark, just 1 km away.</li>" +
                    "<li><b>Maharajbagh Zoo</b> - Perfect for a family outing (2 km).</li>" +
                    "<li><b>Empress Mall</b> - Shopping and entertainment (1.5 km).</li>" +
                    "<li><b>Zero Mile Stone</b> - The geographical center of India (1.8 km).</li>" +
                    "</ul>" +

                    "<h3>🍽️ Dining Options</h3>" +
                    "<p>Enjoy delicious <b>street food at Sitabuldi Market</b> or dine at nearby restaurants offering " +
                    "Maharashtrian and North Indian delicacies.</p>" +

                    "<h3>🏨 Hotel Amenities</h3>" +
                    "<ul>" +
                    "<li>✅ 24/7 Room Service</li>" +
                    "<li>✅ Free Wi-Fi</li>" +
                    "<li>✅ Business Center & Meeting Rooms</li>" +
                    "<li>✅ Airport Shuttle Service</li>" +
                    "</ul>" +

                    "<h3>🛏️ Room Features</h3>" +
                    "<p>Our rooms come with <b>air conditioning, LED TV, coffee maker</b>, and premium bedding for a comfortable stay.</p>" +

                    "<h3>🌆 Book Your Stay Now!</h3>" +
                    "<p>Explore Nagpur with a budget-friendly and comfortable stay at Orange City Inn.</p>" +
                    "</body></html>");
            intent.putExtra("price", 2500);
        } else if (v.getId() == R.id.lakeview) {
            intent.putExtra("hotel_name", "Lakeview Residency");
            intent.putExtra("hotel_location", "Ambazari Road, Nagpur, Maharashtra 440033");
            intent.putExtra("hotel_price", "₹3,800/night");
            intent.putExtra("hotel_image", R.drawable.room4);
            intent.putExtra("hotel_description", "Enjoy serene lake views with modern amenities.");
            intent.putExtra("hotel_html", "<html><body>" +
                    "<h2>🏨 Welcome to Lakeview Residency</h2>" +
                    "<p>Escape into tranquility at <b>Lakeview Residency</b>, " +
                    "a serene retreat overlooking the beautiful <b>Ambazari Lake</b>. " +
                    "Perfect for nature lovers and business travelers alike!</p>" +

                    "<h3>📍 Nearby Attractions</h3>" +
                    "<ul>" +
                    "<li><b>Ambazari Lake & Garden</b> - Just 500m away, enjoy boating & evening walks.</li>" +
                    "<li><b>Futala Lake</b> - 4 km, famous for its stunning fountains and food street.</li>" +
                    "<li><b>Japanese Garden</b> - 3 km, a beautifully landscaped space for relaxation.</li>" +
                    "<li><b>Balaji Temple</b> - 2.5 km, a peaceful place for spiritual seekers.</li>" +
                    "</ul>" +

                    "<h3>🍽️ Dining Experience</h3>" +
                    "<p>Our in-house <b>rooftop restaurant</b> offers scenic lake views while serving " +
                    "fresh seafood, Indian, and continental cuisine.</p>" +

                    "<h3>🏨 Hotel Facilities</h3>" +
                    "<ul>" +
                    "<li>✅ Infinity Pool Overlooking the Lake</li>" +
                    "<li>✅ Spa & Wellness Center</li>" +
                    "<li>✅ Complimentary Breakfast</li>" +
                    "<li>✅ High-Speed Wi-Fi & Business Lounge</li>" +
                    "</ul>" +

                    "<h3>🛏️ Luxurious Room Features</h3>" +
                    "<p>All rooms are equipped with <b>private balconies, lake-view seating, mini-fridge</b>, and a plush king-sized bed.</p>" +

                    "<h3>🌅 Relax and Rejuvenate</h3>" +
                    "<p>Book your stay at <b>Lakeview Residency</b> and enjoy a perfect getaway in Nagpur.</p>" +
                    "</body></html>");
            intent.putExtra("price", 3800);

        }

        startActivity(intent);
    };
}



// Inflate the menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.toolbar_menu, menu);
//        return true;
//    }
//
//    // Handle menu item clicks
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_edit) {
//            // Handle Edit button click
//            return true;
//        }
//        return super.onOptionsItemSelected(item);