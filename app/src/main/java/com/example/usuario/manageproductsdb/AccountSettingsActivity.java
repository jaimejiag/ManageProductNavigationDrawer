package com.example.usuario.manageproductsdb;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by jaime on 7/11/16.
 */

public class AccountSettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.account_settings);
    }
}
