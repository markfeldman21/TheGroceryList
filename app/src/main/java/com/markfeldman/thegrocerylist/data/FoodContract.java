package com.markfeldman.thegrocerylist.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class FoodContract {

    public final static String CONTENT_AUTHORITY ="com.markfeldman.thegrocerylist";
    public final static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH ="food_table";

    public static final class FoodList implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        public static final String TABLE_NAME = "food_table";
        public static final String ITEM_NAME = "item";
    }
}
