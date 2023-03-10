package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class display_list extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public ArrayList<AdModel> initializeAdList(ArrayList<AdModel> adModelArrayList, DBManager dbManager) {
        for (int d = 0; d < dbManager.getIDs().length; d++) {
            AdModel ad = dbManager.getById((int) dbManager.getIDs()[d]);
            adModelArrayList.add(ad);
        }
        return adModelArrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        int permission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.addfloatingActionButton);

        DBManager dbManager = DBManager.getDBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        ArrayList<AdModel> adModelArrayList = new ArrayList<AdModel>();
        adModelArrayList = initializeAdList(adModelArrayList, dbManager);
        RecyclerViewAdAdapter adapter2 = new RecyclerViewAdAdapter(adModelArrayList);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setAdapter(adapter2);

        add.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lancementSecondeActivite = new Intent(display_list.this, AdAddActivity.class);
                startActivity (lancementSecondeActivite);
            }
        });
    }
}