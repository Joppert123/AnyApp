package com.example.anyapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

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

public class ChangeDetailsFragment extends Fragment implements View.OnClickListener {

    private EditText editTextUsername, editTextEmail;
    private Button buttonChangeDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_change_details, container, false);

        editTextUsername = view.findViewById(R.id.cud_editTextUsername);
        editTextEmail = view.findViewById(R.id.cud_editTextEmail);

        buttonChangeDetails = view.findViewById(R.id.cud_buttonAanpassen);

        buttonChangeDetails.setOnClickListener(this);

        return view;
    }

    private void changeDetails(){
        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final int user_id = SharedPrefManager.getInstance(getActivity()).getID();

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
                                    getActivity(),
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
                params.put("email", email);
                params.put("id", String.valueOf(user_id));
                return params;
            }
        };

        RequestHandler.getInstance(getActivity()).addToRequestQueue(sr);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonChangeDetails){
            changeDetails();
        }

    }
}
