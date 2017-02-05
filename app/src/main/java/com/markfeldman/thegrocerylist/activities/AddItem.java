package com.markfeldman.thegrocerylist.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.markfeldman.thegrocerylist.fragments.AddItemFrag;
import com.markfeldman.thegrocerylist.R;

public class AddItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.list_item_container,new AddItemFrag()).commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
