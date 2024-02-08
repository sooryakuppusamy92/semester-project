package com.app.paisa.budget;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.app.paisa.R;
import android.widget.Button;
import android.widget.TextView;

public class BudgetMenu extends AppCompatActivity {
    private Button buttonAddBuget;
    private Button buttonAddExpense;
    private Button buttonViewExpense;
    Intent intent;
    String username=null;

    //private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_menu);


        intent = getIntent();
        username = intent.getStringExtra("username");

        //Displaying header for menu screen
        TextView header=findViewById(R.id.txtUsernameHeader);
        header.setText("Welcome "+username);

        //Declaring each element in screen
        buttonAddBuget = findViewById(R.id.buttonAddBuget);
        buttonAddExpense=findViewById(R.id.buttonAddExpense);
        buttonViewExpense=findViewById(R.id.buttonViewExpense);

        //Creating button functionality
        buttonAddBuget.setOnClickListener(view -> {
            intent = new Intent(this, AddBudget.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        buttonAddExpense.setOnClickListener(view -> {
            intent = new Intent(this, AddExpense.class);
            intent.putExtra("username", username);
            startActivity(intent);

        });
        buttonViewExpense.setOnClickListener(view -> {
            intent = new Intent(this, ViewExpense.class);
            intent.putExtra("username", username);
            startActivity(intent);

        });

    }
}