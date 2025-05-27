package com.example.miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_USERS = "users";
    public static final String TABLE_ROOM_BOOKINGS = "room_bookings";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "email TEXT UNIQUE, " +
            "mobile TEXT, " +
            "password TEXT);";

    private static final String CREATE_TABLE_ROOM_BOOKINGS = "CREATE TABLE IF NOT EXISTS " + TABLE_ROOM_BOOKINGS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_id INTEGER, " +
            "hotel_id INTEGER, " +
            "room_number INTEGER, " +
            "check_in_date TEXT, " +
            "check_out_date TEXT, " +
            "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_ROOM_BOOKINGS);
        Log.d("DatabaseHelper", "Tables Created Successfully: " + TABLE_USERS + ", " + TABLE_ROOM_BOOKINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM_BOOKINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean isRoomAvailable(int hotelId, int roomNumber, String checkInDate, String checkOutDate) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_ROOM_BOOKINGS +
                " WHERE hotel_id = ? AND room_number = ? AND NOT (" +
                "check_out_date <= ? OR check_in_date >= ?)";

        Cursor cursor = db.rawQuery(query, new String[]{
                String.valueOf(hotelId),
                String.valueOf(roomNumber),
                checkInDate,
                checkOutDate
        });

        boolean isAvailable = !cursor.moveToFirst();
        cursor.close();
        return isAvailable;
    }

    public long insertRoomBooking(int userId, int hotelId, int roomNumber, String checkInDate, String checkOutDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("hotel_id", hotelId);
        values.put("room_number", roomNumber);
        values.put("check_in_date", checkInDate);
        values.put("check_out_date", checkOutDate);

        long result = db.insert(TABLE_ROOM_BOOKINGS, null, values);
        db.close();
        return result;
    }
}
