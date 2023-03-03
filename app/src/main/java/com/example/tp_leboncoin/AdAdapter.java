package com.example.tp_leboncoin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tp_leboncoin.R;

import java.util.ArrayList;

public class AdAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<AdModel> adModelArrayList;
    private final LayoutInflater inflater;
    // Constructor
    public AdAdapter(Context context, ArrayList<AdModel> adModelArrayList) {
        this.context = context;
        this.adModelArrayList = adModelArrayList;
        inflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount() { return adModelArrayList.size() ; } // Return ad number
    @Override
    public AdModel getItem(int i) { return adModelArrayList.get(i) ; } // Return ad number i
    @Override
    public long getItemId(int i) { return i ; } // Return ad id i
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AdModel ad = getItem(i) ;
        view = inflater.inflate(R.layout.item_listview_ad, null);
// Get the image view and both text views
        ImageView imageIV = (ImageView) view.findViewById(R.id.imageView);
        TextView titleTV = (TextView) view.findViewById(R.id.material_title_textview);
        TextView addressTV = view.findViewById(R.id.material_address_textview) ;
        imageIV.setImageResource(ad.getImage());
        titleTV.setText(ad.getTitle());
        addressTV.setText(ad.getAddress());
        return view;
    }
}