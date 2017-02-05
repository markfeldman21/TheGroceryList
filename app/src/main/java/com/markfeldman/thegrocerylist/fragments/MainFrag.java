package com.markfeldman.thegrocerylist.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.markfeldman.thegrocerylist.R;
import com.markfeldman.thegrocerylist.activities.AddItem;

public class MainFrag extends Fragment {
    private FloatingActionButton floatingActionButton;


    public MainFrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AddItem.class);
                startActivity(i);
            }
        });

        return view;
    }

}
