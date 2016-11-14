package com.example.usuario.manageproductsrecycler.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.usuario.manageproductsrecycler.interfaces.IPreferences;

/**
 * Created by usuario on 10/11/16.
 */

public class AccountPreferences implements IPreferences{
    private static IPreferences accountPreferences;
    //public static final String FILE = "com.example.usuario.manageproductsrecycler_preferences";  Es el id de la app (en Proyect Structure)
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    private SharedPreferences sharedPreferences;

    private AccountPreferences(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    //Singleton de la clase.
    public static IPreferences getInstance (Context context){
        if (accountPreferences == null) {
            accountPreferences = new AccountPreferences(context);
        }

        return accountPreferences;
    }

    public void putUser (String user){
        getEditor().putString(USER, user).apply();
    }

    public void putPassword (String password){
        getEditor().putString(PASSWORD, password).apply();
    }

    public void putEmail (String email){
        getEditor().putString(EMAIL, email).apply();
    }

    private SharedPreferences.Editor getEditor(){
        return sharedPreferences.edit();
    }
}
