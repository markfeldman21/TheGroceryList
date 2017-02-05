package com.markfeldman.thegrocerylist.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.markfeldman.thegrocerylist.R;


public class AddItemFrag extends Fragment {
    private EditText input;
    private Button addBtn;


    public AddItemFrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        input = (EditText)view.findViewById(R.id.input);
        addBtn = (Button)view.findViewById(R.id.add_button);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Content Resolver and Save Through Content Provider
            }
        });

        return view;
    }

}
