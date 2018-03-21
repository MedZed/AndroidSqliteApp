package com.example.med.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        myTextView = (TextView) findViewById(R.id.adminActivityTextView);


        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        myTextView.setText("Welcome Admin :"+ nameFromIntent);

    }
}
