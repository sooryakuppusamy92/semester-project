package com.app.paisa.budget;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserBudgetDAO extends SQLiteOpenHelper {
    public UserBudgetDAO(Context context) {
        super(context,"userbudget.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase) {
        System.out.println("inside dbmanage");
        SQLiteDatabase.execSQL("create table userbudget(BudgetAmount REAL,Month varchar(12),CustomerName varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase SQLiteDatabase, int oldVersion, int newVersion) {
        String update="DROP TABLE userbudget IF EXISTS userbudget";
        SQLiteDatabase.execSQL(update);
        onCreate(SQLiteDatabase);
    }
}
