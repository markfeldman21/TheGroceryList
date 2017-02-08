package com.markfeldman.thegrocerylist.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

public class GroceryContentProvider extends ContentProvider {
    final static int FOOD_ITEM = 100;
    final static int FOOD_ITEM_WITH_ID = 101;
    private UriMatcher uriMatcher = buildUriMatcher();
    private final String TAG = "GROCERY_PROVIDER";

    public static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(FoodContract.CONTENT_AUTHORITY,FoodContract.PATH,FOOD_ITEM);
        uriMatcher.addURI(FoodContract.CONTENT_AUTHORITY,FoodContract.PATH + "/#",FOOD_ITEM_WITH_ID);
        return uriMatcher;
    }



    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        FoodDatabase foodDatabase = new FoodDatabase(getContext());
        int match = uriMatcher.match(uri);

        switch (match){
            case FOOD_ITEM:{
                foodDatabase.openReadableDB();
                cursor = foodDatabase.getAllRows(projection,selection,selectionArgs,sortOrder);

                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = uriMatcher.match(uri);
        Uri returnUri;
        switch (match){
            case FOOD_ITEM:{
                Context context = getContext();
                FoodDatabase foodDatabase = new FoodDatabase(context);
                foodDatabase.openWriteableDB();
                long id = foodDatabase.insertRow(values);
                if(id>0){
                    returnUri = ContentUris.withAppendedId(FoodContract.FoodList.CONTENT_URI,id);
                }else{
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        int taskDeleted;
        switch (match){
            case FOOD_ITEM_WITH_ID:{
                String idForDeletion = uri.getPathSegments().get(1);
                FoodDatabase foodDatabase = new FoodDatabase(getContext());
                foodDatabase.openWriteableDB();
                taskDeleted = foodDatabase.deleteRow(FoodContract.FoodList.TABLE_NAME,"_id=?",new String[]{idForDeletion});
                break;
            }
            default:
                throw new UnsupportedOperationException("URI UNKNOWN " + uri);
        }
        if(taskDeleted!=0){
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return taskDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
