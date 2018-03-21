package com.example.med.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by med on 18-Mar-18.
 */
import com.example.med.sqlite.helper.InputValidation;
import com.example.med.sqlite.model.User;
import com.example.med.sqlite.sql.DatabaseHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initObjects();

    }
    private EditText textInputEditTextEmail;
    private TextInputLayout textInputLayoutEmail;

    private EditText textInputEditTextPassword;
    private TextInputLayout textInputLayoutPassword;


    private Button ButtonRegister;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;
    private final AppCompatActivity activity = LoginActivity.this;




    private void initViews(){


        textInputEditTextEmail = (EditText) findViewById(R.id.login_email);
        textInputEditTextPassword = (EditText) findViewById(R.id.login_password);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);


        //ButtonRegister = (Button) findViewById(R.id.button_signin);

    }
    private void initObjects(){
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
    }

    public void loginButton(View view) {
        //Toast.makeText(RegisterActivity.this,"please Create your Account First", Toast.LENGTH_SHORT).show();
        verifyFromSQLite();
    }
    public void createAccountLink(View view){
        Intent myInetnt = new Intent(this, RegisterActivity.class);
        startActivity(myInetnt);
    }

    private void verifyFromSQLite(){
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_fill_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_fill_password))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {

            if(databaseHelper.returnRole(textInputEditTextEmail.getText().toString().trim()).equals("Admin"))
            {
                Intent adminIntent = new Intent(activity, AdminActivity.class);
                adminIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                startActivity(adminIntent);
            }
            else
            {
                Intent profileIntent = new Intent(activity, ProfileActivity.class);
                profileIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                startActivity(profileIntent);
            }


        } else {
            Toast.makeText(activity,(R.string.error_message_wrong_login), Toast.LENGTH_SHORT).show();
        }
    }

}