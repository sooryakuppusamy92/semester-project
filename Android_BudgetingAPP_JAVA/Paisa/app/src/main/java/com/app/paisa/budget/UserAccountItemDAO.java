package com.app.paisa.budget;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserAccountItemDAO extends SQLiteOpenHelper {
    public UserAccountItemDAO(Context context) {
        super(context,"useritem.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase) {
        System.out.println("inside dbmanage");
        SQLiteDatabase.execSQL("create table useritem(Month varchar(12),EnterItem varchar(20),ItemCost REAL,CustomerName varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase SQLiteDatabase, int oldVersion, int newVersion) {
        String update="DROP TABLE useritem IF EXISTS useritem";
        SQLiteDatabase.execSQL(update);
        onCreate(SQLiteDatabase);
    }
}
