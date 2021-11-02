package com.edurda77.filmlibrary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.domain.NovPlaiyngMovie
import com.edurda77.filmlibrary.ui.SearchActivity
import java.util.ArrayList


private var toolbar: Toolbar? = null

class MainActivity : AppCompatActivity() {
    private var movies = listOf<NovPlaiyngMovie>(
        NovPlaiyngMovie("Терминатор",1984, 10.0),
        NovPlaiyngMovie("Терминатор2",1991, 10.0)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        var movie : NovPlaiyngMovie
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setToolbar()
        setRecycledView()

    }

    fun setToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
    fun setRecycledView() {
        val recyclerView: RecyclerView = findViewById(R.id.now_playng)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = PlaiyngMovieAdapter(movies)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == R.id.action_search) {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        if (item.getItemId() == R.id.custom) {
            val intent = Intent(this, CustomActivity::class.java)
            startActivity(intent)
        }
        if (item.getItemId() == R.id.about) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}

