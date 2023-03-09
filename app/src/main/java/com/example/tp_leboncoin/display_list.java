package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class display_list extends AppCompatActivity {

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Title");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation icon click here
            }
        });

        DBManager dbManager = DBManager.getDBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        ArrayList<AdModel> adModelArrayList = new ArrayList<AdModel>();
        adModelArrayList = initializeAdList(adModelArrayList, dbManager);
        RecyclerViewAdAdapter adapter2 = new RecyclerViewAdAdapter(adModelArrayList);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setAdapter(adapter2);
    }
}