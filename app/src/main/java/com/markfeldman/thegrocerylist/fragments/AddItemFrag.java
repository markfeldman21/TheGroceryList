package com.markfeldman.thegrocerylist.fragments;


import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.markfeldman.thegrocerylist.R;
import com.markfeldman.thegrocerylist.data.FoodContract;


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
                String userInput = input.getText().toString();
                if (userInput.length()==0){
                    Toast.makeText(getActivity(),R.string.input_error,Toast.LENGTH_LONG).show();
                    return;
                }
                ContentValues cv = new ContentValues();
                cv.put(FoodContract.FoodList.ITEM_NAME,userInput);
                Uri uri = getActivity().getContentResolver().insert(FoodContract.FoodList.CONTENT_URI,cv);
                Toast.makeText(getActivity(),R.string.item_inserted,Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });

        return view;
    }

}
