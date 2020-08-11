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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername, editTextEmail, editTextPassword;
    private Button buttonRegistreer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = (EditText) findViewById(R.id.re_editTextEmail);
        editTextUsername = (EditText) findViewById(R.id.re_editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.re_editTextPassword);

        buttonRegistreer = (Button) findViewById(R.id.re_buttonRegistreer);

        buttonRegistreer.setOnClickListener(this);
    }

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        StringRequest sr = new StringRequest(Request.Method.POST,
                Config.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("SUCCES", response);
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", error.toString());
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", email);
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(sr);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegistreer){
            registerUser();
        }
    }
}