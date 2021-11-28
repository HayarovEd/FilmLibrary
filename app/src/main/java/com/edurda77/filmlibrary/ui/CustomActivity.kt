package com.edurda77.filmlibrary.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.databinding.ActivityCustomBinding
import android.preference.PreferenceManager




class CustomActivity : AppCompatActivity() {
    val APP_PREFERENCES  : String = "mysettings"
    lateinit var mSettings: SharedPreferences
    var adult: Boolean=false

    private lateinit var binding: ActivityCustomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mSettings = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        SetPreferences()
        binding.checkAdult.setOnClickListener()
    }
    private fun Switch.setOnClickListener() {
        adult = isChecked
        SetPreferences()
    }
    private fun SetPreferences(){
        val editor = mSettings.edit()
        editor.putBoolean(APP_PREFERENCES, adult)
        editor.apply()
    }
}


