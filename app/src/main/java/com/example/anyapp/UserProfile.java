package com.example.anyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    private TextView textAccountUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        if(!SharedPrefManager.getInstance(this).userLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        textAccountUsername = (TextView) findViewById(R.id.up_textAccountUsername);

        textAccountUsername.setText(SharedPrefManager.getInstance(this).getUsername());
    }
}