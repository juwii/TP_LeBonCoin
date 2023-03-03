package com.example.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class display_list extends AppCompatActivity {

    public ArrayList<AdModel> initializeAdList(ArrayList<AdModel> adModelArrayList) {
        for(int i = 0; i < 20; i++) {
            AdModel ad = new AdModel("Title " + i, "Address " + i, R.drawable.wood);
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
        setContentView(R.layout.activity_display_list);
        ListView lv = (ListView) findViewById(R.id.listview_materials);
        lv.setAdapter(ads);
        System.out.println(ads.adModelArrayList.get(0).getAddress());
        System.out.println(ads.adModelArrayList.size());
    }
}