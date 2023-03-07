package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        DBManager dbManager = DBManager.getDBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        CursorAdapter adapter = new DbAdAdapter(this, cursor, R.layout.item_listview_ad);
        adapter.notifyDataSetChanged();

        ListView lv = (ListView) findViewById(R.id.listview_materials);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent lv_details = new Intent(display_list.this, AdViewActivity.class);
                lv_details.putExtra("Title", dbManager.getById((int) id).getTitle());
                lv_details.putExtra("Address", dbManager.getById((int) id).getAddress());
                lv_details.putExtra("Phone", dbManager.getById((int) id).getTelephone_number());
                if(dbManager.getById((int) id).getExternalPathImage() != null) {
                    lv_details.putExtra("Picture", dbManager.getById((int) id).getExternalPathImage());
                    lv_details.putExtra("Type_picture", "glide");
                } else if(dbManager.getById((int) id).getInternalPathImage() != null) {
                    lv_details.putExtra("Picture", dbManager.getById((int) id).getInternalPathImage());
                    lv_details.putExtra("Type_picture", "bitmap");
                } else {
                    lv_details.putExtra("Type_picture", "default");
                }
                startActivity (lv_details);
            }
        });
    }
}