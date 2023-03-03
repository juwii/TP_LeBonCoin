package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class display_list extends AppCompatActivity {

    public ArrayList<AdModel> initializeAdList(ArrayList<AdModel> adModelArrayList) {
        for(int i = 0; i < 20; i++) {
            AdModel ad = new AdModel("Title " + i, "Address " + i, R.drawable.wood, "+33666666666" + i);
            adModelArrayList.add(ad);
        }
        return adModelArrayList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<AdModel> adModelArrayList = new ArrayList<AdModel>();
        AdAdapter ads = new AdAdapter(this.getBaseContext(), adModelArrayList);
        initializeAdList(ads.adModelArrayList);

        Intent i = getIntent();
        String m1 = i.getStringExtra ("Title");
        String m2 = i.getStringExtra ("Address");
        String m3 = i.getStringExtra ("Phone");

        if(m1 != null && m2 != null && m3 != null){
            AdModel ad = new AdModel(m1, m2, R.drawable.wood, m3);
            ads.adModelArrayList.add(ad);
        }

        setContentView(R.layout.activity_display_list);
        ListView lv = (ListView) findViewById(R.id.listview_materials);
        lv.setAdapter(ads);
        System.out.println(ads.adModelArrayList.get(0).getAddress());
        System.out.println(ads.adModelArrayList.size());
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