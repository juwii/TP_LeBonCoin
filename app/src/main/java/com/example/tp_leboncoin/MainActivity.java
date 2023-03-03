package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.display_list_button);
        Button b2 = (Button) findViewById(R.id.add_material_button);
        b1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lancementSecondeActivite = new Intent(MainActivity.this, display_list.class);
                startActivity (lancementSecondeActivite);
            }
        });
        b2.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lancementSecondeActivite = new Intent(MainActivity.this, AdAddActivity.class);
                startActivity (lancementSecondeActivite);
            }
        });
    }
}