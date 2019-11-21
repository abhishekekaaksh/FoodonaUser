package com.foodona.foodona.Restarant.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class MyDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Foodoan";
    public static final String TABLE_NAME_1 = "OrderDetails";
    public static final String KEY_1 = "cat_id";
    public static final String KEY_2 = "cat_name";
    public static final String KEY_3 = "sub_cat_id";
    public static final String KEY_4 = "sub_cat_name";
    public static final String KEY_5 = "sub_cat_price";
    public static final String KEY_6 = "sub_cat_desc";
    public static final String KEY_7 = "sub_cat_photo";

    private static final int DB_VERSION = 1;
    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE " + TABLE_NAME_1 + "(" + KEY_1 + "id integer primary key autoincrement," + KEY_2 + "TEXT," + KEY_3 + "TEXT,"
                + KEY_4 + "TEXT," + KEY_5 + "TEXT," + KEY_6 + "TEXT," + KEY_7 + "BLOB" + ")";
        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        // Create tables again
        onCreate(db);
    }

    // Adding new User Details
    public void insertUserDetails(String cat_id, String cat_name, String sub_cat_id,String sub_cat_name,String sub_cat_price,String sub_cat_desc,String sub_cat_image) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_1, cat_id);
        cValues.put(KEY_2, cat_name);
        cValues.put(KEY_3, sub_cat_id);
        cValues.put(KEY_4, sub_cat_name);
        cValues.put(KEY_5, sub_cat_price);
        cValues.put(KEY_6, sub_cat_desc);
        cValues.put(KEY_7, sub_cat_image);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME_1, null, cValues);
        db.close();
    }
    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, location, designation FROM "+ TABLE_NAME_1;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("sub_cat_name",cursor.getString(cursor.getColumnIndex(KEY_4)));
            user.put("sub_cat_price",cursor.getString(cursor.getColumnIndex(KEY_5)));
            user.put("sub_cat_id",cursor.getString(cursor.getColumnIndex(KEY_3)));
            userList.add(user);
        }
        return  userList;
    }
    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, location, designation FROM "+ TABLE_NAME_1;
        Cursor cursor = db.query(TABLE_NAME_1, new String[]{KEY_3, KEY_5, KEY_4}, KEY_3+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("sub_cat_name",cursor.getString(cursor.getColumnIndex(KEY_3)));
            user.put("sub_cat_price",cursor.getString(cursor.getColumnIndex(KEY_5)));
            user.put("sub_cat_id",cursor.getString(cursor.getColumnIndex(KEY_4)));
            userList.add(user);
        }
        return  userList;

    }
    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_1, KEY_3+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String location, String designation, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_5, location);
        cVals.put(KEY_4, designation);
        int count = db.update(TABLE_NAME_1, cVals, KEY_3+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}
