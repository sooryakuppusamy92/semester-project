package com.app.paisa.budget;

import android.content.Context;

public interface BudgetManager {

    long addBudget(Context context, float budgetAmount, String month, String customerName);

    CustomerBudget getBudget(Context context, String customerName, String month);

    float getBalanceLeft(Context context, String customerName, String month);

    long addExpense(Context context, String item, float itemAmount, String month, String customerName);
}