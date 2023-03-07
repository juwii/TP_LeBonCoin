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
    public ArrayList<AdModel> initializeAdList(ArrayList<AdModel> adModelArrayList, DBManager dbManager) {
        for(int d = 0; d < dbManager.getIDs().length; d++) {
            AdModel ad = dbManager.getById((int) dbManager.getIDs()[d]);
            adModelArrayList.add(ad);
        }
        return adModelArrayList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBManager dbManager = DBManager.getDBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        CursorAdapter adapter = new DbAdAdapter(this, cursor, R.layout.activity_display_list);
        adapter.notifyDataSetChanged();

        ArrayList<AdModel> adModelArrayList = new ArrayList<AdModel>();
        AdAdapter ads = new AdAdapter(this.getBaseContext(), adModelArrayList);

        Intent i = getIntent();
        String m1 = i.getStringExtra ("Title");
        String m2 = i.getStringExtra ("Address");
        String m3 = i.getStringExtra ("Phone");

        if (m1 != null && m2 != null && m3 != null) {
            dbManager.insert(new AdModel(m1, m2, null, "C:\\Users\\cedri\\AndroidStudioProjects\\TP_LeBonCoin\\app\\src\\main\\res\\drawable\\wood.png", m3));
        }

        initializeAdList(ads.adModelArrayList, dbManager);
        setContentView(R.layout.activity_display_list);
        ListView lv = (ListView) findViewById(R.id.listview_materials);
        lv.setAdapter(ads);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent lv_details = new Intent(display_list.this, AdViewActivity.class);
                lv_details.putExtra("Title", ads.adModelArrayList.get((int) id).getTitle());
                lv_details.putExtra("Address", ads.adModelArrayList.get((int) id).getAddress());
                lv_details.putExtra("Phone", ads.adModelArrayList.get((int) id).getTelephone_number());
                startActivity (lv_details);
            }
        });
    }
}