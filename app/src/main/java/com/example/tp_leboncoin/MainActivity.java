package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        Button b1 = (Button) findViewById(R.id.display_list_button);
        Button b2 = (Button) findViewById(R.id.add_material_button);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.CAMERA}, 100);
        }
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