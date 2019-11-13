package com.example.projectwithsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MyDBAdapter extends SQLiteOpenHelper {

    public MyDBAdapter(@Nullable Context context) {
        super(context,"SimpleWordsDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table wordsList (ID INTEGER PRIMARY KEY AUTOINCREMENT,LABEL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert_word(String word) {
        SQLiteDatabase dbb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LABEL", word);
        long result = dbb.insert("wordsList",null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * FROM wordsList",null);
        return cursor;
    }


}
