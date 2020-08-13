package com.example.anyapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Joppe, based on code from developers.android.com
 */

public class SharedPrefManager {
        private static SharedPrefManager instance;
        private static Context ctx;

        private static final String SHARED_PREF_NAME = "sharedpref";
        private static final String KEY_USERNAME = "username";
        private static final String KEY_USER_EMAIL = "useremail";
        private static final String KEY_USER_ID = "userid";

        private SharedPrefManager(Context context) {
            ctx = context;
        }

        public static synchronized SharedPrefManager getInstance(Context context) {
            if (instance == null) {
                instance = new SharedPrefManager(context);
            }
            return instance;
        }

        public boolean loginUser(int id, String username, String email){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt(KEY_USER_ID, id);
            editor.putString(KEY_USERNAME, username);
            editor.putString(KEY_USER_EMAIL, email);

            editor.apply();

            return true;
        }

        public boolean userLoggedIn(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            if(sharedPreferences.getString(KEY_USERNAME, null) != null){
                return true;
            }
            return false;
        }

        public String getUsername(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_USERNAME, null);
        }

        public String getEmail(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_USER_EMAIL, null);
        }

        public Integer getID(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getInt(KEY_USER_ID, 0);
        }

        public boolean logoutUser(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            return true;
        }
}
