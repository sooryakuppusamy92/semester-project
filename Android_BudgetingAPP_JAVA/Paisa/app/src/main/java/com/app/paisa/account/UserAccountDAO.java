package com.app.paisa.account;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserAccountDAO extends SQLiteOpenHelper {
    public UserAccountDAO(@Nullable Context context) {
        super(context,"useraccount.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase) {
        System.out.println("inside dbmanage");
        SQLiteDatabase.execSQL("create table useraccount(username varchar(20),password varchar(12),createdon TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase SQLiteDatabase, int oldVersion, int newVersion) {
        String update="DROP TABLE useraccount IF EXISTS useraccount";
        SQLiteDatabase.execSQL(update);
        onCreate(SQLiteDatabase);
    }
}
