package com.example.testtask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

public class DatabaseManager {

        private DatabaseHelper databasebHelper;

        private Context context;

        private SQLiteDatabase dbQuery;

        public DatabaseManager(Context c) {
            context = c;
        }

        public DatabaseManager open() throws SQLException {
            databasebHelper = new DatabaseHelper(context);
            dbQuery = databasebHelper.getWritableDatabase();
            return this;
        }

        public void close() {
            databasebHelper.close();
        }

        public void insertIntoTable(String btcText, String satoshiText) {
            ContentValues contentValue = new ContentValues();
            contentValue.put(DatabaseHelper.USER_BTC, btcText);
            contentValue.put(DatabaseHelper.USER_SATOSHI, satoshiText);
            dbQuery.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        }

        public Cursor fetchRowsFromTable() {
            String[] columns = new String[] {
                    DatabaseHelper._ID, DatabaseHelper.USER_BTC, DatabaseHelper.USER_SATOSHI };
            Cursor cursor = dbQuery.query(DatabaseHelper.TABLE_NAME, columns, null,
                    null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor;
        }
    // Update Method is not in used in this version
        public int updateTheRow(long _id, String btcText, String satoshiText) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.USER_BTC, btcText);
            contentValues.put(DatabaseHelper.USER_SATOSHI, satoshiText);
            int i = dbQuery.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
            return i;
        }
    // Delete Method is also not in used in this version
        public void deleteTheRow(long _id) {
            dbQuery.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
        }



}
