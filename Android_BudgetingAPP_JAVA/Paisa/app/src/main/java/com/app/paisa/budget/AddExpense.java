package com.app.paisa.budget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.paisa.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddExpense extends AppCompatActivity {
    Intent intent;

    private EditText BudgetLeft, EnterItem,ItemCost;
    private Button saveItem;
    String username=null;
    String month=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        intent = getIntent();
        username = intent.getStringExtra("username");


        BudgetLeft = findViewById(R.id.BudgetLeft);
        EnterItem = findViewById(R.id.EnterItem);
        ItemCost = findViewById(R.id.ItemCost);
        saveItem = findViewById(R.id.saveItem);

        DateFormat dateFormat = new SimpleDateFormat("MMMM");
        Date date = new Date();
        month=dateFormat.format(date);

        BudgetManager data=new CustomerBudget();

        float budget=data.getBalanceLeft(AddExpense.this,username,month);
        BudgetLeft.setText(String.valueOf(budget));
        BudgetLeft.setEnabled(false);

        saveItem.setOnClickListener(view -> {
            float itemAmount=Float.parseFloat(ItemCost.getText().toString());
            float balanceLeft=Float.parseFloat(String.valueOf(budget));
            float overspending=balanceLeft-itemAmount;
            if(overspending<0)
            {
                Toast.makeText(AddExpense.this,  "Attention:Overspending.Please control your expense:"+username,Toast.LENGTH_LONG).show();
            }
            long row = data.addExpense(AddExpense.this,EnterItem.getText().toString(), itemAmount,month, username);
            //Toast.makeText(AddExpense.this,  "Successfully saved expense for customer :"+username, Toast.LENGTH_SHORT).show();
            intent = new Intent(this, BudgetMenu.class);
            intent.putExtra("username", username);
            startActivity(intent);

        });







    }
}