package com.example.anyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangeUserDetails extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername, editTextEmail;

    private Button buttonChangeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_details);

        editTextUsername = (EditText) findViewById(R.id.cud_editTextUsername);
        editTextEmail = (EditText) findViewById(R.id.cud_editTextEmail);

        buttonChangeDetails = (Button) findViewById(R.id.cud_buttonAanpassen);

        buttonChangeDetails.setOnClickListener(this);
    }

    private void changeDetails(){
        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final int user_id = SharedPrefManager.getInstance(this).getID();

        StringRequest sr = new StringRequest(
                Request.Method.POST,
                Config.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("LOG", response);
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(
                                    getApplicationContext(),
                                    response,
                                    Toast.LENGTH_LONG
                            ).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("LOG", error.toString());
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("id", String.valueOf(user_id));
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(sr);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonChangeDetails){
            changeDetails();
        }

    }
}