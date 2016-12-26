package com.safercrypt.goandroidsm2;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pavelsafronov on 26.12.16.
 */

public class SharedPrefLogin {
    private final static String APP_PREFERENCES = BuildConfig.APPLICATION_ID;
    private final static String FIRST_NAME = "first_name";
    private final static String LAST_NAME = "second_name";

    private SharedPreferences sharedPreferences;

    public SharedPrefLogin (Context context){
        this.sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setFirstName(String firstName){
        sharedPreferences.edit().putString(FIRST_NAME, firstName).apply();
    }

    public String getFirstName(){
        return sharedPreferences.getString(FIRST_NAME, "");
    }

    public void setLastName(String secondName){
        sharedPreferences.edit().putString(LAST_NAME, secondName).apply();
    }

    public String getLastName(){
        return sharedPreferences.getString(LAST_NAME, "");
    }

}

