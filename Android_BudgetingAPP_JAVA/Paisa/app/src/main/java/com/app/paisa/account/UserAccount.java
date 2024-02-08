package com.app.paisa.account;

import android.content.Context;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAccount implements UserAccountInterface{
    UserAccountDAO databaseHelper;
    SQLiteDatabase db;
    @Override
    public long addCustomer(Context Context, String Username, String Password) {

        databaseHelper = new UserAccountDAO(Context);
        db=databaseHelper.getWritableDatabase();

         //inserting customer data into contentvalue object
        ContentValues values=new ContentValues();
        values.put("username",Username);
        values.put("password",Password);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        values.put("createdon", dateFormat.format(date));
        System.out.println(values);

        //inserting customer data into useraccount database
        long row=db.insert("useraccount",null,values);
        System.out.println("row inserted"+row);

        return row;
    }

    @Override
    public boolean authenticateCustomer(Context Context, String EnteredUsername, String Enteredpassword) {
        Boolean result = false;
        String username = null;
        String password = null;

        databaseHelper = new UserAccountDAO(Context);
        db = databaseHelper.getReadableDatabase();

        //checking username and password entered
        String projection[] = {"username", "password", "createdon"};
        Cursor rows = db.query("useraccount", projection, null, null, null, null, null);
        if (rows.getCount() != 0) {
            rows.moveToFirst();
            while (!rows.isAfterLast()) {
                username = rows.getString(0).toString();
                password = rows.getString(1).toString();
                if (username.equals(EnteredUsername) && password.equals(Enteredpassword))
                    return true;
                else {
                    rows.moveToNext();
                }
            }
        }
        rows.close();

        return result;
    }
}
