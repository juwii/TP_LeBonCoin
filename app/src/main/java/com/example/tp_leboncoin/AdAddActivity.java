package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_add);
        Button b1 = (Button) findViewById(R.id.send_button);
        EditText Title = (EditText) findViewById(R.id.material_title_edittext);
        EditText Address = (EditText) findViewById(R.id.material_address_edittext);
        EditText Phone = (EditText) findViewById(R.id.material_phone_edittext);
        b1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sent = new Intent(AdAddActivity.this, display_list.class);
                sent.putExtra("Title", Title.getText().toString());
                sent.putExtra("Address", Address.getText().toString());
                sent.putExtra("Phone", Phone.getText().toString());
                startActivity (sent);
            }
        });
    }
}