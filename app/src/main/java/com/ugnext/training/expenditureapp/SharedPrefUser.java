package com.ugnext.training.expenditureapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUser {
    private static SharedPrefUser instance;
    private static Context ctx;

    private static final String SHARED_PREF_NAME = "sharedprefLogin";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_ID = "id";

    private SharedPrefUser(Context context) {
        ctx = context;
    }

    public static synchronized SharedPrefUser getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefUser(context);
        }
        return instance;
    }

    public boolean userLogin(int id, String email){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_EMAIL, email);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_EMAIL, null) == null){
            return false;
        }
        return  true;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public String getKeyUserEmail() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public int getKeyUserId() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_ID, 0);
    }
}

