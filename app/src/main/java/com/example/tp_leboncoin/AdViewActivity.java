package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AdViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_view);
        TextView Title = (TextView) findViewById(R.id.material_title_textview);
        TextView Address = (TextView) findViewById(R.id.material_address_textview);
        TextView Phone = (TextView) findViewById(R.id.material_phone_textview);
        Intent i = getIntent();
        String m1 = i.getStringExtra ("Title");
        String m2 = i.getStringExtra ("Address");
        String m3 = i.getStringExtra ("Phone");
        Title.setText (m1+"");
        Address.setText (m2+"");
        Phone.setText (m3+"");

    }
}