package com.example.med.sqlite;


import com.example.med.sqlite.helper.InputValidation;
import com.example.med.sqlite.model.User;
import com.example.med.sqlite.sql.DatabaseHelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initObjects();
    }
    private final AppCompatActivity activity = RegisterActivity.this;



    private EditText textInputEditTextEmail;
    private TextInputLayout textInputLayoutEmail;

    private EditText textInputEditTextPassword;
    private TextInputLayout textInputLayoutPassword;

    private EditText textInputEditTextConfirmPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private EditText textInputEditTextSurname;
    private TextInputLayout textInputLayoutSurname;

    private EditText textInputEditCIN;
    private TextInputLayout textInputLayoutCIN;

    private EditText textInputEditTextName;
    private TextInputLayout textInputLayoutName;
    //private Button ButtonRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;




    private void initViews(){

        textInputEditTextEmail = (EditText) findViewById(R.id.register_email);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputEditTextPassword = (EditText) findViewById(R.id.register_password);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextConfirmPassword = (EditText) findViewById(R.id.register_confirm_password);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputEditTextName = (EditText) findViewById(R.id.register_name);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputEditTextSurname = (EditText) findViewById(R.id.register_surname);
        textInputLayoutSurname = (TextInputLayout) findViewById(R.id.textInputLayoutSurname);
        textInputEditCIN = (EditText) findViewById(R.id.register_cin);
        textInputLayoutCIN = (TextInputLayout) findViewById(R.id.textInputLayoutCIN);

        //ButtonRegister = (Button) findViewById(R.id.button_register);

    }
    private void initObjects(){
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }



    public void leButton(View view) {
        //Toast.makeText(RegisterActivity.this,"please Create your Account First", Toast.LENGTH_SHORT).show();
        postDataToSQLite();
    }

    public void logtoAccountLink(View view){
        Intent myInetnt = new Intent(this, LoginActivity.class);
        startActivity(myInetnt);
    }


    private void postDataToSQLite(){

        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_fill_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSurname, textInputLayoutSurname, getString(R.string.error_message_fill_surname))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditCIN, textInputLayoutCIN, getString(R.string.error_message_fill_ncin))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_fill_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_fill_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }


               if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
                    user.setName(textInputEditTextName.getText().toString().trim());
                   user.setSurname(textInputEditTextSurname.getText().toString().trim());
                   user.setEmail(textInputEditTextEmail.getText().toString().trim());
                    user.setCin(textInputEditCIN.getText().toString().trim());
                   user.setRole("user");
                   user.setPassword(textInputEditTextPassword.getText().toString().trim());
                   databaseHelper.addUser(user);
                    databaseHelper.addUser(user);
                Toast.makeText(activity, R.string.success_db_message, Toast.LENGTH_SHORT).show();
                //emptyInputEditText();
                   Intent myInetnt = new Intent(this, LoginActivity.class);
                   startActivity(myInetnt);

            }else{
                Toast.makeText(activity,(R.string.error_db_message), Toast.LENGTH_SHORT).show();
            }








}
    /*private void emptyInputEditText(){
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }*/
}