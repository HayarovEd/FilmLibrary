package com.edurda77.filmlibrary.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Switch
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.databinding.ActivityCustomBinding
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.edurda77.filmlibrary.domain.NoteDao
import com.edurda77.filmlibrary.domain.NoteRepo
private const val DEFAUL_KEY = "DEFAUL_KEY"

class CustomActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null

    var adult: Boolean = false
    private val noteDao: NoteDao by lazy { app.noteDao }
    private val prefernces: SharedPreferences by lazy {getPreferences(MODE_PRIVATE)}
    private lateinit var binding: ActivityCustomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()


        binding.checkAdult.setOnClickListener()
        binding.clearNots.setOnClickListener {
            Thread {
                noteDao.clearNots()
            }.start()
            Toast.makeText(this,"Теперь заметок нет", Toast.LENGTH_SHORT).show()
        }
    }

    private fun Switch.setOnClickListener() {
        adult = isChecked

    }

    private fun SetPreferences() {
        prefernces.edit().let {
            it.putBoolean(DEFAUL_KEY,adult)
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
        when (item.itemId) {
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.nots -> {
                val intent = Intent(this, NotesActivity::class.java)
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
    override fun onStart() {
        super.onStart()
        binding.checkAdult.isChecked=prefernces.getBoolean(DEFAUL_KEY, false)
    }
    override fun onStop() {
        SetPreferences()
        super.onStop()
    }
}


