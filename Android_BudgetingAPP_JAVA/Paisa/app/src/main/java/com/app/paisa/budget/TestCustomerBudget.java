package com.app.paisa.budget;

import android.content.Context;

public class TestCustomerBudget implements BudgetManager {
    public String customerName;
    public float budgetAmount;
    public float balanceLeft;
    public String Month;


    @Override
    public long addBudget(Context context, float BudgetAmount, String Month, String CustomerName) {
        return 0;
    }

    @Override
    public CustomerBudget getBudget(Context context, String CustomerName, String month) {
        return null;
    }


    public TestCustomerBudget getBudget1(Context context, String CustomerName, String month) {

        TestCustomerBudget Customer=new TestCustomerBudget();
        Customer.customerName="testuser";
        Customer.Month="april";
        Customer.budgetAmount=101;
        Customer.balanceLeft=1;

        return Customer;
    }

    @Override
    public float getBalanceLeft(Context context, String CustomerName, String month) {
        return 0;
    }

    @Override
    public long addExpense(Context context, String Item, float itemAmount, String Month, String CustomerName) {
        return 0;
    }
}
