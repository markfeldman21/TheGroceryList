package com.markfeldman.thegrocerylist.data;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.markfeldman.thegrocerylist.R;

public class FoodRecyclerView extends RecyclerView.Adapter<FoodRecyclerView.RecyclerHolder> {
    public Cursor cursor;

    public FoodRecyclerView(){
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
        cursor.moveToPosition(position);
        int idIndex = cursor.getInt(cursor.getColumnIndex(FoodContract.FoodList._ID));
        String item = cursor.getString(cursor.getColumnIndex(FoodContract.FoodList.ITEM_NAME));
        holder.item.setText(item);
        holder.itemView.setTag(idIndex);

    }

    @Override
    public int getItemCount() {
        if (cursor == null){
            return 0;
        }

        return cursor.getCount();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView item;
        public RecyclerHolder(View itemView) {
            super(itemView);
            item = (TextView)itemView.findViewById(R.id.item);
        }
    }

    public void swap (Cursor cursor){
        this.cursor = cursor;
        notifyDataSetChanged();
    }
}
