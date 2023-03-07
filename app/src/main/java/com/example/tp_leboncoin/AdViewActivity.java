package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

public class AdViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_view);
        TextView Title = (TextView) findViewById(R.id.material_title_textview);
        TextView Address = (TextView) findViewById(R.id.material_address_textview);
        TextView Phone = (TextView) findViewById(R.id.material_phone_textview);
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Intent i = getIntent();
        String m1 = i.getStringExtra ("Title");
        String m2 = i.getStringExtra ("Address");
        String m3 = i.getStringExtra ("Phone");
        String m4 = i.getStringExtra("Picture");
        String m5 = i.getStringExtra("Type_picture");
        Title.setText (m1+"");
        Address.setText (m2+"");
        Phone.setText (m3+"");
        if(m5.equals("glide")) {
            Glide.with(this).load(m4).into(image);
        } else if (m5.equals("bitmap")) {
            File imgFile = new File(m4);
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                image.setImageBitmap(myBitmap);// Glide is a library to insert an image into an imageview with a url.
            }
        } else {
            String image2 = "@drawable/wood";
            File imgFile = new File(image2);
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                image.setImageBitmap(myBitmap);
            }
        }

    }
}