package com.edurda77.filmlibrary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.databinding.ActivityAboutBinding
import com.edurda77.filmlibrary.databinding.ActivityMainBinding

class AboutActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAboutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()

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