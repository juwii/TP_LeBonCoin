package com.example.tp_leboncoin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdAdapter extends
        RecyclerView.Adapter<RecyclerViewAdAdapter.RecyclerViewHolder> {
    private List<AdModel> data;
    public RecyclerViewAdAdapter(List<AdModel> data) {this.data = data;}

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_material, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
// This method is called for each of the visible rows displayed in our RecyclerView. It is
        usually here that we update their appearance.
                AdModel ad = data.get(...);
        holder.titleTextView.setText(...);
        holder.addressTextView.setText(...);
        holder.imageView.setImageResource(...);
    }
    @Override
    public int getItemCount() {return data.size();}
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView titleTextView;
        public final TextView addressTextView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(...);
            titleTextView = itemView.findViewById(...);
            addressTextView = itemView.findViewById(...);
        }
    }
}