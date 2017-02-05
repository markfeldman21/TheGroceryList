package com.markfeldman.thegrocerylist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDatabase {












    private class DatabaseHelper extends SQLiteOpenHelper {
        public static final int DB_VERSION = 1;
        public static final String DB_NAME = "food_list_db";
        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
