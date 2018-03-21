package com.example.med.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayLoginActivity(View view){
        Intent myInetnt = new Intent(this, LoginActivity.class);
        startActivity(myInetnt);
    }
    public void displayRegisterActivity(View view){
        Intent myInetnt = new Intent(this, RegisterActivity.class);
        startActivity(myInetnt);
    }

}
