package com.markfeldman.thegrocerylist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FoodDatabase {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;
    private Context context;
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "food_list_db";

    private final String CREATE_DATABASE = "CREATE TABLE " + FoodContract.FoodList.TABLE_NAME + " ("
            + FoodContract.FoodList._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FoodContract.FoodList.ITEM_NAME + " TEXT NOT NULL"
            + ");";


    public FoodDatabase(Context context){
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public FoodDatabase openWriteableDB(){
        db = databaseHelper.getWritableDatabase();
        return this;
    }

    public FoodDatabase openReadableDB(){
        db = databaseHelper.getReadableDatabase();
        return this;
    }

    public long insertRow(ContentValues cv){
        return db.insert(FoodContract.FoodList.TABLE_NAME,null,cv);
    }

    public Cursor getAllRows(String[] projection, String selection, String[] selectionArgs, String sortOrder){
        return db.query(FoodContract.FoodList.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
    }

    public int deleteRow(String table, String whereClause, String[]whereArgs){
        return db.delete(table,whereClause,whereArgs);
    }

    public void closeDB(){
        db.close();
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DATABASE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + FoodContract.FoodList.TABLE_NAME);
            Log.v ("DATABASE", "*********DATABASE DROPPED AND RECREATED*********");
            onCreate(db);
        }
    }
}
