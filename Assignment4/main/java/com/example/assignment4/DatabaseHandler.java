package com.example.assignment4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    DatabaseHandler(Context context) {
        super(context, "sqlDB", null, 1);
    }

    public void onCreate(SQLiteDatabase sqlDB1) {
        sqlDB1.execSQL("CREATE TABLE teams (id INTEGER PRIMARY KEY, city TEXT, name TEXT, sport TEXT, MVP TEXT, image BLOB)");
    }
    public void onUpgrade(SQLiteDatabase sqlDB2, int oldVer, int newVer) {
        sqlDB2.execSQL("DROP TABLE IF EXISTS teams");
        onCreate(sqlDB2);
    }

    public void insertItem(String city, String name, String sport, String MVP, String image){
        SQLiteDatabase sqlDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("city", city);
        values.put("name", name);
        values.put("sport", sport);
        values.put("MVP", MVP);
        values.put("image", image);
        sqlDB.insert("teams", null, values);
        sqlDB.close();
    }

    public List<String> getAllItems(String colName){
        List<String> listItems = new ArrayList<>();
        SQLiteDatabase sqlDB = getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM teams", null);
        if (cursor.moveToFirst()){
            do {
                listItems.add(cursor.getString(cursor.getColumnIndex(colName)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqlDB.close();
        return listItems;
    }

    public void updateItem(String id, String city, String name, String sport, String MVP, String image){
        SQLiteDatabase sqlDB = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("city", city);
        contentValues.put("name", name);
        contentValues.put("sport", sport);
        contentValues.put("MVP", MVP);
        contentValues.put("image", image);

        sqlDB.update("teams", contentValues, "id = " + id, null);
    }

    public void deleteItem(String id){
        SQLiteDatabase sqlDB = getWritableDatabase();
        sqlDB.delete("teams", "id = " + id , null);
        sqlDB.close();
    }
}
