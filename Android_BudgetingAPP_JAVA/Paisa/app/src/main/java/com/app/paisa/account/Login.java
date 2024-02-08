package com.app.paisa.account;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.app.paisa.R;
import com.app.paisa.budget.BudgetMenu;

public class Login extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private TextView txtSignUp;
    private Button buttonLogin;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        txtSignUp = findViewById(R.id.txtSignUp);

        txtSignUp.setOnClickListener(view -> {
            Toast.makeText(Login.this, "signup clicked", Toast.LENGTH_SHORT).show();
            intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);

        });


        // Set a click listener for the login button
        buttonLogin.setOnClickListener(view -> {
            // Retrieve entered username and password
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            UserAccountInterface dataBase = new UserAccount();
            // Implement authentication logic here
            if (dataBase.authenticateCustomer(this, username, password)) {
                // Successful login
                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, BudgetMenu.class);
                intent.putExtra("username", username);
                startActivity(intent);

            } else {
                // Failed login
                Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}