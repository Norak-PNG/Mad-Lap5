package com.example.myapplication.fragment;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;


import com.example.myapplication.R;

import java.util.Locale;

public class SettingFrag extends PreferenceFragmentCompat {
    private static final String PREF_LANGUAGE = "app_language";

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);


        ListPreference languagePreference = findPreference(PREF_LANGUAGE);
        if (languagePreference != null) {
            languagePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                Log.d("SettingFrag", "Language preference changed to: " + newValue + " " + preference.getKey());

                Locale locale = new Locale(newValue.toString());
                Locale.setDefault(locale);

                Resources resources = requireActivity().getResources();
                Configuration config = new Configuration();
                config.setLocale(locale);
                resources.updateConfiguration(config, resources.getDisplayMetrics());

                requireActivity().recreate();
                return true;
            });
        }
    }

}