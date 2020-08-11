package com.example.anyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
//package com.example.anyapp;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
////    private EditText editTextUsername, editTextEmail, editTextPassword;
////    private Button buttonRegistreer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login2);
//
////        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
////        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
////        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
////
////        buttonRegistreer = (Button) findViewById(R.id.buttonRegistreer);
////
////        buttonRegistreer.setOnClickListener(this);
//    }
//
////    private void registerUser(){
////        final String email = editTextEmail.getText().toString().trim();
////        final String username = editTextUsername.getText().toString().trim();
////        final String password = editTextPassword.getText().toString().trim();
////
////        StringRequest sr = new StringRequest(Request.Method.POST,
////                Config.URL_REGISTER,
////                new Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String response) {
////                        try {
////                            JSONObject jsonObject = new JSONObject(response);
////
//////                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
////                            Log.e("SUCCES", response);
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////
////                    }
////                },
////                new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
//////                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
////                        Log.e("ERROR", error.toString());
////                    }
////                }){
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                Map<String,String> params = new HashMap<>();
////                params.put("username", username);
////                params.put("email", email);
////                params.put("password", password);
////                return params;
////            }
////        };
////        RequestQueue queue = Volley.newRequestQueue(this);
////        queue.add(sr);
////
////    }
//
////    @Override
////    public void onClick(View v) {
////        if (v == buttonRegistreer){
////            registerUser();
////        }
////    }