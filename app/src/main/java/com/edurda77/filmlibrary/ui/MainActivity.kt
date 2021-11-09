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
import com.edurda77.filmlibrary.domain.FilmGenre
import com.edurda77.filmlibrary.domain.Movie
import android.widget.Toast
import com.edurda77.filmlibrary.ui.MovieAdapter.OnStateClickListener


private var toolbar: Toolbar? = null
val movies: ArrayList<Movie> = ArrayList()
class MainActivity : AppCompatActivity() {
    private var action = listOf(
        Movie("Терминатор", "action", "120 min",10.0, 1984, 1.0, 3.3, "fgfgfgfgfg"),
        Movie("Терминатор2", "action", "120 min",10.0, 1992, 1.4, 10.0, "fgfgfgfgfg"),
    )
    private var camedy = listOf(
        Movie("Амерканский пирог", "action", "120 min",10.0, 1984, 1.0, 3.3, "fgfgfgfgfg"),
        Movie("Американский пирог2", "action", "120 min",10.0, 1992, 1.4, 10.0, "fgfgfgfgfg"),
    )
    private var triller = listOf(
        Movie("Цвет ночи", "action", "120 min",10.0, 1984, 1.0, 3.3, "fgfgfgfgfg"),
        Movie("Семь", "action", "120 min",10.0, 1992, 1.4, 10.0, "fgfgfgfgfg"),
    )
    private var ganre = listOf(
        FilmGenre("Боевик", action),
        FilmGenre("Комедия", camedy),
        FilmGenre("Триллер", triller)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        //var movie : Movie

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setToolbar()

        setOotRecycledView()

    }

    fun setToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
    fun setOotRecycledView() {
        val recyclerView: RecyclerView = findViewById(R.id.out_recycled_view)

        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = OutAdapter(ganre)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()){
            R.id.action_search ->{
                Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.custom ->{
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)}
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

