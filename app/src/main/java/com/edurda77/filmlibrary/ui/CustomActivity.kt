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
    //var preferences = PreferenceManager.getDefaultSharedPreferences(this)
    var adult: Boolean=false

    private lateinit var binding: ActivityCustomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //binding.checkAdult.setOnClickListener()
    }
    /*private fun Switch.setOnClickListener() {
        adult = isChecked
        SetPreferences()
    }
    private fun SetPreferences(){
        val editor = preferences.edit()
        editor.putBoolean("SetAdult", adult)
        editor.apply()
    }*/
}


