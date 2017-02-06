package com.markfeldman.thegrocerylist.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.markfeldman.thegrocerylist.R;

/**
 * Created by markfeldman on 2/6/17.
 */

public class FoodRecyclerView extends RecyclerView.Adapter<FoodRecyclerView.RecyclerHolder> {
    public String[]foodList;

    public FoodRecyclerView(String[] input){
        this.foodList = input;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutID = R.layout.individual_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutID,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.item.setText(foodList[position]);

    }

    @Override
    public int getItemCount() {
        return foodList.length;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView item;
        public RecyclerHolder(View itemView) {
            super(itemView);
            item = (TextView)itemView.findViewById(R.id.item);
        }
    }
}
