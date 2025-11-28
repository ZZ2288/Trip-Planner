package com.example.myapplication;

import android.content.Context;

import androidx.recyclerview.widget.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.*;
import android.widget.TextView;

import androidx.recyclerview.widget.*;

import java.util.*;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    Context context;
    List<Items> items;

    public ItemAdapter(Context cont, List<Items> items) {
        this.context = cont;
        this.items = items;

    }
    @Override
    public int getItemCount() {
        return this.items.size();
    }
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_style, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder itemholder, int position) {
        Items item = items.get(position);
        itemholder.name.setText(item.name);
        itemholder.country.setText(item.country);
        itemholder.location.setText(item.Location);
        itemholder.date.setText(item.date);
        itemholder.type.setText(item.type);

    }
    public void updateList(List<Items> newList) {
        this.items = newList;
        notifyDataSetChanged();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        TextView name, country, location, date, type;

        public ItemHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.name);
            this.country = view.findViewById(R.id.country);
            this.location = view.findViewById(R.id.location);
            this.date = view.findViewById(R.id.date);
            this.type = view.findViewById(R.id.type);

        }

    }


}
