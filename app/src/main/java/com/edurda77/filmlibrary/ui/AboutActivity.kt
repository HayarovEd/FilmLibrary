package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.edurda77.filmlibrary.BuildConfig
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.databinding.ActivityAboutBinding


class AboutActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private var aboutTextView: TextView? = null
    private lateinit var binding: ActivityAboutBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAboutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        aboutTextView=binding.about
        aboutTextView?.text=aboutTextView?.text.toString()+"Тип сборки:  " +BuildConfig.BUILD_TYPE


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
}