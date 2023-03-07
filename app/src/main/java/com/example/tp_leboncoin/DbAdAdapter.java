package com.example.tp_leboncoin;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

public class DbAdAdapter extends CursorAdapter {
    private final int item_layout;

    public DbAdAdapter(Context context, Cursor c, int layout) {
        super(context, c);
        item_layout = layout;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(item_layout, viewGroup, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleTextView = (TextView) view.findViewById(R.id.material_title_textview);
        TextView addressTextView = (TextView) view.findViewById(R.id.material_address_textview);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper._ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TITLE));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ADDRESS));
        String image;
        if(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGE_EXT)) != null) {
            image = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGE_EXT));
            Glide.with(view).load(image).into(imageView);
        } else if (cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGE_INT)) != null) {
            image = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGE_INT));
            File imgFile = new File(image);
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);// Glide is a library to insert an image into an imageview with a url.
            }
        } else {
            image = "@drawable/wood";
            File imgFile = new File(image);
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);// Glide is a library to insert an image into an imageview with a url.
            }
        }

        titleTextView.setText(title);
        addressTextView.setText(address);
    }
}