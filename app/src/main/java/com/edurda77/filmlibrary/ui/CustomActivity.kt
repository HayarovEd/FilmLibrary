package com.edurda77.filmlibrary.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.databinding.ActivityCustomBinding
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.edurda77.filmlibrary.domain.NoteDao

private const val DEFAULT_KEY = "DEFAULT_KEY"


class CustomActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null


    private val noteDao: NoteDao by lazy { app.noteDao }
    private val preferences: SharedPreferences by lazy {app.sharedPreferences}
    private lateinit var binding: ActivityCustomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()



        binding.clearNots.setOnClickListener {
            Thread {
                noteDao.clearNotes()
            }.start()
            Toast.makeText(this,"Теперь заметок нет", Toast.LENGTH_SHORT).show()
        }
    }



    private fun setPreferences() {
        preferences.edit().let {
            it.putBoolean(DEFAULT_KEY,binding.checkAdult.isChecked)
            it.commit()
        }
    }

    private fun setToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemMenu = MenuDelegate(item)
        itemMenu.setMenu(this,item)
        return super.onOptionsItemSelected(item)
    }
    override fun onStart() {
        super.onStart()
        binding.checkAdult.isChecked=preferences.getBoolean(DEFAULT_KEY, false)
    }
    override fun onStop() {
        setPreferences()
        super.onStop()
    }
}


