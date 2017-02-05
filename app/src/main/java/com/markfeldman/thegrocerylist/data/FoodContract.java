package com.markfeldman.thegrocerylist.data;

import android.provider.BaseColumns;

public class FoodContract {

    public static class FoodList implements BaseColumns {
        private static final String TABLE_NAME = "food_table";
        public static final String ITEM_NAME = "item";
    }
}
