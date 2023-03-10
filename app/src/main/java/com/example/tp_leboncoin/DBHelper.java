package com.example.tp_leboncoin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "MATERIAL";

    // Table columns
    public static final String _ID = "_id";
    public static final String TITLE = "MATERIAL_TITLE";
    public static final String ADDRESS = "MATERIAL_ADDRESS";
    public static final String IMAGE_EXT = "MATERIAL_EXTERNAL_PATH";
    public static final String IMAGE_INT = "MATERIAL_INTERNAL_PATH";
    public static final String PHONE_NUMBER = "MATERIAL_PHONE_NUMBER";
    public static final String EMAIL = "MATERIAL_EMAIL";
    // Database Information
    static final String DB_NAME = "LEBONCOIN.DB";

    // database version
    static final int DB_VERSION = 18;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + "_id"
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT NOT NULL, " + ADDRESS + " TEXT, " + IMAGE_EXT + " TEXT, " + IMAGE_INT + " TEXT, " + PHONE_NUMBER + " TEXT, " + EMAIL + " TEXT);";

    public DBHelper(Context context) {
        super(context,
                DB_NAME,
                null,
                DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @SuppressLint("Range")
    public long[] getIDs() {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT _id FROM "+TABLE_NAME;
        Cursor data = db.rawQuery("SELECT * FROM MATERIAL",new String[] {});
        if (data.getCount() > 0) {
            long id[] = new long[data.getCount()];
            int i = 0;
            data.moveToFirst();
            do {
                id[i] = data.getLong(data.getColumnIndex("_id"));
                i++;
            } while (data.moveToNext());
            data.close();
            return id;
        }
        else{
            return null;
        }
    }

    // Util if you want to add a clicklistener on specific ad in listview.
    public AdModel getById(long id) {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" where "+ "_id" + "=?";
        Cursor data = db.rawQuery(query,new String[] {String.valueOf(id)});
        if (data != null) {
            data.moveToFirst();
        }
        else{
            return null;
        }

        String title = data.getString(data.getColumnIndexOrThrow(TITLE));
        String address = data.getString(data.getColumnIndexOrThrow(ADDRESS));
        String phone_number = data.getString(data.getColumnIndexOrThrow(PHONE_NUMBER));
        String email = data.getString(data.getColumnIndexOrThrow(EMAIL));
        return new AdModel(title, address, data.getString(data.getColumnIndexOrThrow(IMAGE_EXT)), data.getString(data.getColumnIndexOrThrow(IMAGE_INT)), phone_number, email);
    }
}