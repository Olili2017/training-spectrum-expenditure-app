package com.ugnext.training.expenditureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private TextView email;
    private TextView userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        email = (TextView) findViewById(R.id.email);
        userID = (TextView) findViewById(R.id.userID);

//        email.setText(SharedPrefUser.getInstance(getApplicationContext()).getKeyUserEmail());
//        userID.setText(SharedPrefUser.getInstance(getApplicationContext()).getKeyUserId());
    }
}