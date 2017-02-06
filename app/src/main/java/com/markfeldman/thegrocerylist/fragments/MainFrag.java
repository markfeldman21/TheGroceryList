package com.markfeldman.thegrocerylist.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.markfeldman.thegrocerylist.R;
import com.markfeldman.thegrocerylist.activities.AddItem;
import com.markfeldman.thegrocerylist.data.FoodContract;
import com.markfeldman.thegrocerylist.data.FoodRecyclerView;

public class MainFrag extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private FloatingActionButton floatingActionButton;
    private static final int LOADER_ID = 1;
    private String [] foodList = {"Tomotoes", "Cabbage","Cheese"};
    private RecyclerView recyclerView;


    public MainFrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.foodRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        FoodRecyclerView foodRecyclerView = new FoodRecyclerView(foodList);
        recyclerView.setAdapter(foodRecyclerView);

        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AddItem.class);
                startActivity(i);
            }
        });

        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch(id){
            case LOADER_ID: {
                Uri foodQueryUri = FoodContract.FoodList.CONTENT_URI;
                String[] projection = {FoodContract.FoodList.ITEM_NAME};
                String sortOrder = FoodContract.FoodList.ITEM_NAME + " COLLATE NOCASE ASC";

                CursorLoader cursorLoader = new CursorLoader(getActivity(),foodQueryUri,projection,null,null,sortOrder);

                return cursorLoader;
            }default:
                throw new RuntimeException("CURSORLOADER NOT IMPLEMENTED: " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
