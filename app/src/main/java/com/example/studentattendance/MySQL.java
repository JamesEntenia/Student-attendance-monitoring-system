package com.example.studentattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MySQL extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Teacher.db";

    //Account database
    public static final String TABLE_ACCOUNT = "Account";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String QUESTION = "QUESTION";
    public static final String ANSWER = "ANSWER";

    //STUDENTS


    public MySQL(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_ACCOUNT+ " (USERNAME TEXT ,PASSWORD TEXT ,QUESTION TEXT ,ANSWER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACCOUNT);
        onCreate(db);
    }

    public void Register(String Username,String Password,String Question,String Answer){

        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(USERNAME,Username);
        content.put(PASSWORD,Password);
        content.put(QUESTION,Question);
        content.put(ANSWER,Answer);

        sq.insert(TABLE_ACCOUNT,null,content);


    }

    public Cursor ViewAccounts()
    {
        SQLiteDatabase sq = this.getWritableDatabase();
        Cursor data = sq.rawQuery("select * from "+ TABLE_ACCOUNT, null);

        return data;
    }

}
