package com.edurda77.filmlibrary.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.databinding.ActivityCustomBinding
import android.preference.PreferenceManager
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.edurda77.filmlibrary.domain.NoteRepo


class CustomActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    val APP_PREFERENCES  : String = "mysettings"
    lateinit var mSettings: SharedPreferences
    var adult: Boolean=false
    private val noteRepo: NoteRepo by lazy { app.noteRepo }

    private lateinit var binding: ActivityCustomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        mSettings = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        SetPreferences()
        binding.checkAdult.setOnClickListener()
        binding.clearNots.setOnClickListener{
            noteRepo.clearNots()
        }
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
    private fun setToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.nots -> {
                val intent = Intent(this, NotsActivity::class.java)
                startActivity(intent)
            }
            R.id.custom -> {
                val intent = Intent(this, CustomActivity::class.java)
                startActivity(intent)
            }
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


