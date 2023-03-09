package com.example.tp_leboncoin;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class RecyclerViewAdAdapter extends
        RecyclerView.Adapter<RecyclerViewAdAdapter.RecyclerViewHolder> {
    private List<AdModel> data;
    public RecyclerViewAdAdapter(List<AdModel> data) {this.data = data;}

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_listview_ad, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
// This method is called for each of the visible rows displayed in our RecyclerView. It is
        //usually here that we update their appearance.
        AdModel ad = data.get(position);
        holder.titleTextView.setText(ad.getTitle());
        holder.addressTextView.setText(ad.getAddress());
        String image;
        if(ad.getExternalPathImage() != null) {
            image = ad.getExternalPathImage();
            Glide.with(holder.itemView).load(image).into(holder.imageView);
        } else if (ad.getInternalPathImage() != null) {
            image = ad.getInternalPathImage();
            File imgFile = new File(image);
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                holder.imageView.setImageBitmap(myBitmap);
            }
        } else {
            image = "@drawable/wood";
            File imgFile = new File(image);
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                holder.imageView.setImageBitmap(myBitmap);
            }
        }
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AdViewActivity.class);
                intent.putExtra("Title", ad.getTitle());
                intent.putExtra("Address", ad.getAddress());
                intent.putExtra("Phone", ad.getTelephone_number());
                intent.putExtra("Email", ad.getEmail());
                if(ad.getExternalPathImage() != null) {
                    intent.putExtra("Picture", ad.getExternalPathImage());
                    intent.putExtra("Type_picture", "glide");
                } else if(ad.getInternalPathImage() != null) {
                    intent.putExtra("Picture", ad.getInternalPathImage());
                    intent.putExtra("Type_picture", "bitmap");
                } else {
                    intent.putExtra("Type_picture", "default");
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {return data.size();}
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView titleTextView;
        public final TextView addressTextView;
        public final CardView cardview;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.material_title_textview);
            addressTextView = itemView.findViewById(R.id.material_address_textview);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }
}