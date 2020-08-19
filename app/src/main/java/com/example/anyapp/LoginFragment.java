package com.example.anyapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joppe
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText editTextUsername, editTextPassword;

    private Button buttonLogin;
    private Button buttonRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_login, container, false);

        //Controleer of user is ingelogd
        if(SharedPrefManager.getInstance(getActivity()).userLoggedIn()){
            Fragment fragment = new ProfileFragment();
            replaceFragment(fragment);
        }

        editTextUsername = view.findViewById(R.id.lo_editTextUsername);
        editTextPassword = view.findViewById(R.id.lo_editTextPassword);

        buttonLogin = view.findViewById(R.id.lo_buttonLogin);
        buttonRegister = view.findViewById(R.id.lo_buttonRegistreren);

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.lo_buttonLogin:
                loginUser();
                break;
            case R.id.lo_buttonRegistreren:
                fragment = new RegisterFragment();
                replaceFragment(fragment);
                break;
        }

    }

    private void loginUser(){
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        StringRequest sr = new StringRequest(
                Request.Method.POST,
                Config.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("LOG", response);
                            JSONObject jsonObject = new JSONObject(response);
                            if(!jsonObject.getBoolean("error")){
                                SharedPrefManager.getInstance(getActivity())
                                        .loginUser(
                                                jsonObject.getInt("id"),
                                                jsonObject.getString("username"),
                                                jsonObject.getString("email")
                                        );

                                Toast.makeText(
                                        getActivity(),
                                        "Ingelogd!",
                                        Toast.LENGTH_LONG
                                ).show();
                                Fragment fragment = new ProfileFragment();
                                replaceFragment(fragment);
                            }else{
                                Log.e("LOG", response);
                                Toast.makeText(
                                        getActivity(),
                                        jsonObject.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
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
                                getActivity(),
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
                params.put("password", password);
                return params;
            }
        };

        RequestHandler.getInstance(getActivity()).addToRequestQueue(sr);
    }


    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack("tag");
        transaction.commit();
    }
}
