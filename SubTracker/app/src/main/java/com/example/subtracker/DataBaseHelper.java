package com.example.subtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "COLUMN_ID";
    private static final String SUBSCRYPTION_TABLE = "SUBSCRYPTION_TABLE";
    public static final String COLUMN_SUBSCRYPTION_NAME = "COLUMN_SUBSCRYPTION_NAME";
    public static final String COLUMN_SUBSCRYPTION_COST = "COLUMN_SUBSCRYPTION_COST";
    public static final String COLUMN_PAYMENT_DAY = "COLUMN_PAYMENT_DAY";

    public DataBaseHelper(@Nullable Context context) {

        super(context, "subscryption.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TableStatement = "CREATE TABLE " + SUBSCRYPTION_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SUBSCRYPTION_NAME + " TEXT, " + COLUMN_SUBSCRYPTION_COST + " INT, " + COLUMN_PAYMENT_DAY + " INT)";

        db.execSQL(TableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(databaseModel subModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SUBSCRYPTION_NAME, subModel.getSubscryptionName());
        cv.put(COLUMN_SUBSCRYPTION_COST, subModel.getCost());
        cv.put(COLUMN_PAYMENT_DAY, subModel.getPaymentDay());


        long insert = db.insert(SUBSCRYPTION_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<databaseModel> getEveryone(){
        List<databaseModel> list = new ArrayList<>();

        String query = "SELECT * FROM "+ SUBSCRYPTION_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                int subscryptionID = cursor.getInt(0);
                String subscryptionName = cursor.getString(1);
                float subscryptionCost = cursor.getFloat(2);
                int paymentDay = cursor.getInt(3);

                databaseModel newCustomer = new databaseModel(subscryptionName, subscryptionCost,paymentDay,subscryptionID);
                list.add(newCustomer);

            }while(cursor.moveToNext());
        }
        else {
            //failure. do not add anything
        }

        cursor.close();
        db.close();
        return list;
    }
}
