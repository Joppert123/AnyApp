package com.example.anyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {

    private TextView textAccountUsername, textAccountEmail;

    private Button buttonChangeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        if(!SharedPrefManager.getInstance(this).userLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        textAccountUsername = (TextView) findViewById(R.id.up_textAccountUsername);
        textAccountEmail = (TextView) findViewById(R.id.up_textAccountEmail);

        buttonChangeDetails = (Button) findViewById(R.id.up_buttonChangeDetails);

        textAccountUsername.setText(SharedPrefManager.getInstance(this).getUsername());
        textAccountEmail.setText(SharedPrefManager.getInstance(this).getEmail());

        buttonChangeDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonChangeDetails){
            startActivity(new Intent(this, ChangeUserDetails.class));
        }
    }
}