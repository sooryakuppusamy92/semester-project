package com.app.paisa.budget;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.app.paisa.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddBudget extends AppCompatActivity {
    Intent intent;
    private EditText EnterAddBudget, EnterAddMonth;
    private Button saveAddBudget;
    String username=null;
    String month=null;
    float budgetAmount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        intent = getIntent();
        username = intent.getStringExtra("username");

        EnterAddBudget = findViewById(R.id.EnterAddBudget);
        EnterAddMonth = findViewById(R.id.EnterAddMonth);
        saveAddBudget = findViewById(R.id.saveAddBudget);

        //Getting month
        DateFormat dateFormat = new SimpleDateFormat("MMMM");
        Date date = new Date();
        month=dateFormat.format(date).toString();

        EnterAddMonth.setText(month);
        EnterAddMonth.setEnabled(false);

        saveAddBudget.setOnClickListener(view -> {
            budgetAmount=Float.parseFloat(EnterAddBudget.getText().toString());
            BudgetManager data = new CustomerBudget();
            long row = data.addBudget(AddBudget.this,budgetAmount,month,username);
            Toast.makeText(AddBudget.this,  "Successfully created budget for "+username, Toast.LENGTH_LONG).show();

            intent = new Intent(this, BudgetMenu.class);
            intent.putExtra("username", username);
            startActivity(intent);

        });


        }

}