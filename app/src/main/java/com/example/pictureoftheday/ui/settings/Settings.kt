package com.example.pictureoftheday.ui.settings

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.pictureoftheday.R

class Settings : PreferenceFragmentCompat() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val defaultView = super.onCreateView(inflater, container, savedInstanceState)
        val newLayout = inflater.inflate(R.layout.settings_fragment, container, false) as ViewGroup
        newLayout.addView(defaultView)
        return newLayout
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        findPreference<ListPreference>(getString(R.string.theme_preference))?.let {
            it.setOnPreferenceChangeListener { _, _ ->
                activity?.recreate()
                true
            }
        }

        findPreference<Preference>("androidSdk")?.let {
            it.summary = getString(R.string.android_sdk_version, Build.VERSION.SDK_INT.toString())
        }
    }
}