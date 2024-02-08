package com.app.paisa.budget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.app.paisa.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewExpense extends AppCompatActivity {
    Intent intent;
    private EditText ViewExpenseMonth, ViewExpenseBudget,ViewExpenseBudgetLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);

        intent = getIntent();
        String username = intent.getStringExtra("username");
        System.out.println("soorya check" +username);

        DateFormat dateFormat = new SimpleDateFormat("MMMM");
        Date date = new Date();
        String month=dateFormat.format(date);

        BudgetManager data=new CustomerBudget();
        CustomerBudget customer=new CustomerBudget();
        customer=data.getBudget(ViewExpense.this,username,month);

        ViewExpenseMonth = findViewById(R.id.ViewExpenseMonth);
        ViewExpenseBudget = findViewById(R.id.ViewExpenseBudget);
        ViewExpenseBudgetLeft = findViewById(R.id.ViewExpenseBudgetLeft);

        ViewExpenseMonth.setText(String.valueOf(customer.Month));
        ViewExpenseMonth.setEnabled(false);

        ViewExpenseBudget.setText(String.valueOf(customer.budgetAmount));
        ViewExpenseBudget.setEnabled(false);

        ViewExpenseBudgetLeft.setText(String.valueOf(customer.balanceLeft));
        ViewExpenseBudgetLeft.setEnabled(false);




    }
}