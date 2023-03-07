package com.example.tp_leboncoin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tp_leboncoin.R;

import java.io.File;
import java.util.ArrayList;

public class AdAdapter extends BaseAdapter {
    private final Context context;
    public final ArrayList<AdModel> adModelArrayList;
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
        ImageView imageIV = (ImageView) view.findViewById(R.id.imageView);
        TextView titleTV = (TextView) view.findViewById(R.id.material_title_textview);
        TextView addressTV = view.findViewById(R.id.material_address_textview) ;
        if(ad.getExternalPathImage() != null) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            Glide.with(view).load(ad.getExternalPathImage()).into(imageIV);
        } else if(ad.getInternalPathImage() != null){
            System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
            File imgFile = new File(ad.getInternalPathImage());
            if(imgFile.exists()) {
                System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageIV.setImageBitmap(myBitmap);// Glide is a library to insert an image into an imageview with a url.
            }
        } else {
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            String image = "@drawable/wood";
            File imgFile2 = new File(image);
            if(imgFile2.exists()){
                System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                // Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                // imageView.setImageBitmap(myBitmap);// Glide is a library to insert an image into an imageview with a url.
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                Glide.with(view).load(image).into(imageIV);
            }
        }
        titleTV.setText(ad.getTitle());
        addressTV.setText(ad.getAddress());
        return view;
    }
}