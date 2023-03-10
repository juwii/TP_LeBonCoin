// Package + Import
package com.example.tp_leboncoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class DBManager {

    public static DBManager DBManager;

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    private DBManager(Context c) {
        context = c;
        //init(); // Useful for adding ads for the first time.
    }

    public static DBManager getDBManager(Context context) {
        if (DBManager == null){
            return new DBManager(context);
        }
        return DBManager;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Add ads manually.
    public void init(){
        open();
        insert(new AdModel("Wood", "2254 Charter Street, Kansas City", "https://media.istockphoto.com/id/134253640/photo/construction-of-a-wooden-roof-frame-underway.jpg?s=612x612&w=0&k=20&c=e5gUkic9LGQWahIdHozOsEzHKy_HtsmvmtOHmYsejSU=", null, "+33783876020", "juliette.letondot@etu.imt-nord-europe.fr"));
        insert(new AdModel("Steel", "Lille", "https://as2.ftcdn.net/v2/jpg/03/91/83/87/1000_F_391838708_4HFADW5beay2VVlnoual6Qi5fWeIaD9V.jpg", null, "+33783876980", "tomnonie@livegolftv.com"));
        insert(new AdModel("Clay", "IMT Nord Europe Douai", "https://constrofacilitator.com/wp-content/uploads/2020/02/clay-in-construction.jpg", null, "+33783569812", "oyatsinina@berkahjaran.xyz"));
        insert(new AdModel("Metal", "44 quai charles de Gaulle Lyon", "https://www.meto-constructions.fr/wp-content/uploads/2018/12/IMG_6067.jpg", null, "+33783984580", "mntaxi@yt-google.com"));
        insert(new AdModel("Glass", "1848 Sugar Camp Road, Owatonna", "https://i0.wp.com/www.tipsnepal.com/wp-content/uploads/2020/09/simple-float-glass-1505049573-3306125.jpeg?resize=500%2C317&quality=100&strip=all&ssl=1", null, "+33680984580", "cedric.prast@etu.imt-nord-europe.fr"));
        insert(new AdModel("Wood", "2643 Davis Lane, Denver", "https://yieldpro.com/wp-content/uploads/2020/08/lumber1.jpg", null, "+3377777755777", "nessunhom@gasss.us"));
    }

    public void insert(AdModel ad) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.TITLE, ad.getTitle());
        contentValue.put(DBHelper.ADDRESS, ad.getAddress());
        contentValue.put(DBHelper.PHONE_NUMBER, ad.getTelephone_number());
        contentValue.put(DBHelper.EMAIL, ad.getEmail());
        if(ad.getExternalPathImage() != null) {
            contentValue.put(DBHelper.IMAGE_EXT, ad.getExternalPathImage());
        } else if(ad.getInternalPathImage() != null) {
            contentValue.put(DBHelper.IMAGE_INT, ad.getInternalPathImage());
        } else {
            contentValue.put(DBHelper.IMAGE_INT, "@drawable/wood.jpg");
        }
        System.out.println(ad.getInternalPathImage());
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns;

        columns = new String[] { DBHelper._ID, DBHelper.TITLE, DBHelper.ADDRESS, DBHelper.IMAGE_EXT, DBHelper.IMAGE_INT, DBHelper.PHONE_NUMBER, DBHelper.EMAIL};

        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, AdModel ad) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TITLE, ad.getTitle());
        contentValues.put(DBHelper.ADDRESS, ad.getAddress());
        contentValues.put(DBHelper.PHONE_NUMBER, ad.getTelephone_number());
        contentValues.put(DBHelper.EMAIL, ad.getEmail());
        String image;
        if(DBHelper.IMAGE_EXT != null) {
            contentValues.put(DBHelper.IMAGE_EXT, ad.getExternalPathImage());
        } else {
            contentValues.put(DBHelper.IMAGE_INT, ad.getInternalPathImage());
        }
        int i = database.update(DBHelper.TABLE_NAME, contentValues, "_id" + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DBHelper.TABLE_NAME, "_id" + "=" + _id, null);
    }

    public AdModel getById(int id){
        return dbHelper.getById(id);
    }

    public long[] getIDs(){
        return dbHelper.getIDs();
    }

}