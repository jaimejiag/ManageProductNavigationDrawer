package com.example.usuario.manageproductsfragments;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by jaime on 7/11/16.
 */

public class GeneralSettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.general_settings);
    }
}