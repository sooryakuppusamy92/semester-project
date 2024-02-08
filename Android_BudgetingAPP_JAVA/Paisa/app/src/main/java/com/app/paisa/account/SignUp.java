package com.app.paisa.account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.paisa.R;
import com.app.paisa.budget.BudgetMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    private EditText Usrname, EnterPassword,RepeatPassword;
    private Button SaveButton;
    String username,password,repeatpwd;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SaveButton=findViewById(R.id.SaveButton);

        SaveButton.setOnClickListener(view -> {
            System.out.println("dssdsdsddssd");
            Usrname = findViewById(R.id.Usrname);
            EnterPassword = findViewById(R.id.EnterPwd);
            RepeatPassword = findViewById(R.id.RepeatPassword);

            username = Usrname.getText().toString();
            password = EnterPassword.getText().toString();
            repeatpwd=RepeatPassword.getText().toString();

            if(!password.equals(repeatpwd))
                Toast.makeText(SignUp.this, "Password not matched", Toast.LENGTH_SHORT).show();
            else {
                UserAccountInterface data = new UserAccount();
                long ROW = data.addCustomer(SignUp.this, username, password);
                Toast.makeText(SignUp.this,  "Successfully created account for "+username, Toast.LENGTH_SHORT).show();

                intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                finish();

            }


        });
    }
}