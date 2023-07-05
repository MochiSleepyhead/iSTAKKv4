package com.capstone.istakk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "InventoryBase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_inventory";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCT = "product_name";
    private static final String COLUMN_QUANTITY = "product_quantity";
    private static final String COLUMN_LOW_INVENTORY = "low_inventory_quantity";
    private static final String COLUMN_PRICE = "product_price";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT + " TEXT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_PRICE + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    
    public void addBook(String product, Integer quantity, Integer price) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        
        cv.put(COLUMN_PRODUCT, product);
        cv.put(COLUMN_QUANTITY, quantity);
        cv.put(COLUMN_PRICE, price);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Log.i("fail","Failed");
        } else {
             Log.i("success","Added successfully!");
        }
        
    }

    public Cursor readAllData () {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
