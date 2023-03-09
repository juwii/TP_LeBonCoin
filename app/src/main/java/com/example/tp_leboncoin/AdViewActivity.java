package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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
        TextView Email =(TextView) findViewById(R.id.material_email_textview);
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Intent i = getIntent();
        String m1 = i.getStringExtra ("Title");
        String m2 = i.getStringExtra ("Address");
        String m3 = i.getStringExtra ("Phone");
        String m4 = i.getStringExtra("Picture");
        String m5 = i.getStringExtra("Type_picture");
        String m6 = i.getStringExtra("Email");
        Title.setText (m1+"");

        String mapUrl = "geo:0,0?q=" + Uri.encode(m2);
        String linkText = m2;
        String htmlString = "<a href=\"" + mapUrl + "\">" + linkText + "</a>";
        Address.setText(Html.fromHtml(htmlString));
        Address.setMovementMethod(LinkMovementMethod.getInstance());

        Phone.setText(Html.fromHtml("<a href=\"tel:" + m3 + "\">" + m3 + "</a>"));
        Phone.setMovementMethod(LinkMovementMethod.getInstance());

        Email.setText(Html.fromHtml("<a href=\"mailto:" + m6 + "\">" + m6 + "</a>"));
        Email.setMovementMethod(LinkMovementMethod.getInstance());

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