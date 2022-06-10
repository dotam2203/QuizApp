package com.api.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SingleDemo {
    private static SingleDemo INSTANCE;
    private static SharedPreferences sharedPreferences;

    public static SingleDemo getInstance() {
        if (INSTANCE == null) {
            synchronized (SingleDemo.class) {
                INSTANCE = new SingleDemo();
            }
        }
        return INSTANCE;
    }

    private SingleDemo() {

    }

    public static void deleteAcc(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void setSharedPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences("account",Context.MODE_PRIVATE);
    }
    public static void setUser(String key, String user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,user);
        editor.apply();
    }
    public static void setPass(String key, String pass){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,pass);
        editor.apply();
    }
    public String getUser(String key) {
        return sharedPreferences.getString(key,null);
    }
    public String getPass(String key) {
        return sharedPreferences.getString(key,null);
    }
    public String getUser(String key, String defUser) {
        return sharedPreferences.getString(key,defUser);
    }
    public String getPass(String key, String defPass) {
        return sharedPreferences.getString(key,defPass);
    }
}
