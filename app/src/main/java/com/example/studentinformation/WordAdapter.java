package com.example.studentinformation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    public ArrayList<table_item> marrayList;

    public WordAdapter(ArrayList<table_item> list) {
        marrayList = list;
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.cell1);
            mTextView2 = itemView.findViewById(R.id.cell2);
            mTextView3 = itemView.findViewById(R.id.cell3);
            mTextView4 = itemView.findViewById(R.id.cell4);

        }
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        WordViewHolder wordViewHolder = new WordViewHolder(v);
        return wordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        table_item carrentItem = marrayList.get(position);
        // pass value
        holder.mTextView.setText(carrentItem.getCell_1());
        holder.mTextView2.setText(carrentItem.getCell_2());
        holder.mTextView3.setText(carrentItem.getCell_3());
        holder.mTextView4.setText(carrentItem.getCell_4());

    }

    @Override
    public int getItemCount() {
        if (marrayList == null)
            return 0;
        return marrayList.size();
    }
}
