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

    public boolean deleteOne(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM " + SUBSCRYPTION_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
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

                databaseModel newSub = new databaseModel(subscryptionName, subscryptionCost,paymentDay,subscryptionID);
                list.add(newSub);

            }while(cursor.moveToNext());
        }
        else {
            //failure. do not add anything
        }

        cursor.close();
        db.close();
        return list;
    }


    public String getNameById(int id){
        String name = new String();

        String query = "SELECT " + COLUMN_SUBSCRYPTION_NAME + " FROM "+ SUBSCRYPTION_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            name = cursor.getString(0);
        }
        else {
            //error
            name = "loading error";
        }

        cursor.close();
        db.close();
        return name;
    }

    public float getCostById(int id){
        float cost;

        String query = "SELECT " + COLUMN_SUBSCRYPTION_COST + " FROM "+ SUBSCRYPTION_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            cost = cursor.getFloat(0);
        }
        else {
            //error
            cost = 0.0f;
        }

        cursor.close();
        db.close();
        return cost;
    }

    public int getPaymentDayById(int id){
        int day;

        String query = "SELECT " + COLUMN_PAYMENT_DAY + " FROM "+ SUBSCRYPTION_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            day = cursor.getInt(0);
        }
        else {
            //error
            day = 0;
        }

        cursor.close();
        db.close();
        return day;
    }

}
