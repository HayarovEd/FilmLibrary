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

private const val DEFAUL_KEY = "DEFAUL_KEY"


class CustomActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null


    private val noteDao: NoteDao by lazy { app.noteDao }
    private val prefernces: SharedPreferences by lazy {app.sharedPrefernces}
    private lateinit var binding: ActivityCustomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCustomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()



        binding.clearNots.setOnClickListener {
            Thread {
                noteDao.clearNots()
            }.start()
            Toast.makeText(this,"Теперь заметок нет", Toast.LENGTH_SHORT).show()
        }
    }



    private fun setPreferences() {
        prefernces.edit().let {
            it.putBoolean(DEFAUL_KEY,binding.checkAdult.isChecked)
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
            R.id.start -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.people_search -> {
                val intent = Intent(this, SearchPeopleActivity::class.java)
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
        setPreferences()
        super.onStop()
    }
}


