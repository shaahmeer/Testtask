package com.example.testtask;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

// This class extends SQLiteOpenHelper three methods
public class DatabaseHelper extends SQLiteOpenHelper {


    // Table Name
    public static final String TABLE_NAME = "USERDATA";

    // Table columns
    public static final String _ID = "_id";
    public static final String USER_SATOSHI = "user_satoshi";
    public static final String USER_BTC = "user_btc";

    // Database Information
    static final String DB_NAME = "NAWAB_SATOSHIDB.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_SATOSHI + " TEXT NOT NULL, " + USER_BTC + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
