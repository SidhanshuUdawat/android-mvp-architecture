package com.app.sid.funwithflags.utils.pref;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private Context context = null;
    private String DEFAULT_VALUE = "";
    private static PrefManager sharedPreferenceManager = null;
    private SharedPreferences sharedPreferences = null;
    public static String SHARED_PREF_KEY = "SHARED_PREFERENCES_MANAGER";

    private PrefManager() {
    }

    public static PrefManager getInstance() {
        if (null == sharedPreferenceManager) {
            sharedPreferenceManager = new PrefManager();
        }
        return sharedPreferenceManager;
    }

    private void setSharedPreferences() {
        if (null == sharedPreferences) {
            sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
        }
    }

    public void initPref(Application mContext) {
        this.context = mContext;
        setSharedPreferences();
    }

    //------ Storing Strings ------
    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //------ Getting Strings ------

    public String getString(String key) {
        return sharedPreferences.getString(key, DEFAULT_VALUE);
    }

    //------ Storing Booleans ------
    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    //------ Getting Booleans ------

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void saveInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int default_val) {
        return sharedPreferences.getInt(key, default_val);
    }

    public void saveLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(String key, long default_val) {
        return sharedPreferences.getLong(key, default_val);
    }

    public void clearAll() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }


    public boolean hasKey(String key) {
        return sharedPreferences.contains(key);
    }
}
