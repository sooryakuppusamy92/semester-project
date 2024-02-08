package com.app.paisa.budget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.widget.Toast;

public class CustomerBudget implements BudgetManager {
    public String customerName;
    public float budgetAmount;
    public float balanceLeft;
    public String Month;
    public CustomerItem item;

    UserBudgetDAO budgetHelper;
    UserAccountItemDAO userAccountHelper;
    SQLiteDatabase db;

    /**
     * addBudget: insert customer entered budget into database table userbudget
     * returns long value->no of rows inserted into table
     */
    @Override
    public long addBudget(Context context, float BudgetAmount, String Month, String CustomerName) {
     long row = 0;

        budgetHelper = new UserBudgetDAO(context);
        db=budgetHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("BudgetAmount",BudgetAmount);
        values.put("Month",Month);
        values.put("CustomerName",CustomerName);

        row=db.insert("userbudget",null,values);

     return row;
    }
    /**
     * addExpense: insert customer expense into database table useritem
     * returns long value->no of rows inserted into table
     */
    @Override
    public long addExpense(Context context,String Item, float itemAmount, String Month, String CustomerName) {
       long row=0;
        userAccountHelper = new UserAccountItemDAO(context);
        db=userAccountHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("EnterItem",Item);
        values.put("ItemCost",itemAmount);
        values.put("Month",Month);
        values.put("CustomerName",CustomerName);

        row=db.insert("useritem",null,values);

       return row;
    }

    @Override
    public CustomerBudget getBudget(Context context, String CustomerName, String month) {
        CustomerBudget customer=new CustomerBudget();

        customer.budgetAmount=getBudgetAmount(context,CustomerName,month);;
        customer.Month=month;
        customer.balanceLeft=getBalanceLeft(context,CustomerName,month);
        customer.customerName=CustomerName;

        return customer;
    }

    public float getBalanceLeft(Context context, String CustomerName, String month)
    {
        float budgetAmount=0;
        float expense=0;
        float balance=0;

        budgetAmount=getBudgetAmount(context,CustomerName,month);
        expense=getTotalExpense(context,CustomerName,month);

        balance=budgetAmount-expense;
        System.out.println("soorya checking moneyleft"+balance);

        return balance;
    }

    public float getBudgetAmount(Context context, String CustomerName, String month)
    {
        float budgetAmount=0;
        budgetHelper = new UserBudgetDAO(context);
        db = budgetHelper.getReadableDatabase();

        String whereClause = "CustomerName = ? and Month = ?";
        String[] whereArgs = new String[] {CustomerName,month};

        //checking username and password entered
        String projection[] = {"max(BudgetAmount) AS BudgetAmount", "Month", "CustomerName"};

        Cursor rows = db.query("userbudget", projection, whereClause, whereArgs, null, null, null);
        if (rows.getCount() != 0) {
            rows.moveToFirst();
            while (!rows.isAfterLast()) {
                budgetAmount = budgetAmount + rows.getFloat(0);
                System.out.println("soorya checking balance"+budgetAmount);
                rows.moveToNext();
            }
        }
        rows.close();
        System.out.println("soorya checking balance"+budgetAmount);
         return budgetAmount;

    }

    public float getTotalExpense(Context context, String CustomerName, String month)
    {
        float totalExpense=0;


        String columns[] = {"ItemCost", "Month", "CustomerName"};
        userAccountHelper = new UserAccountItemDAO(context);
        db = userAccountHelper.getReadableDatabase();

        String whereClause = "CustomerName = ? and Month = ?";
        String[] whereArgs = new String[] {CustomerName,month};


        Cursor rows = db.query("useritem", columns, whereClause, whereArgs, null, null, null);
        if (rows.getCount() != 0) {
            rows.moveToFirst();
            while (!rows.isAfterLast()) {
                totalExpense = totalExpense + rows.getFloat(0);
                System.out.println("soorya checking balance"+budgetAmount);
                rows.moveToNext();
            }
        }
        rows.close();
        System.out.println("soorya checking balance"+totalExpense);
        return totalExpense;

    }








}
