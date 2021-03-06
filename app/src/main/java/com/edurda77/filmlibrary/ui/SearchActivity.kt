package com.edurda77.filmlibrary.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultSearchMovie
import com.edurda77.filmlibrary.databinding.ActivitySearchBinding
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCaseSync

private const val DEFAULT_KEY = "DEFAULT_KEY"
class SearchActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var binding: ActivitySearchBinding
    private val goSearchMovie: TheMDBRepoUseCaseSync by lazy { app.theMDBRepoUseCaseSync }
    private val resultSearch = emptyList<ResultSearchMovie>().toMutableList()
    private val preferences: SharedPreferences by lazy {app.sharedPreferences}



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        val adult= preferences.getBoolean(DEFAULT_KEY,false)

        binding.goSearchMovie.setOnClickListener {
            resultSearch.clear()
            val searchString = binding.searchMovie.text.toString()
            Thread {
                goSearchMovie.getReposForSearchMovieSync(searchString, adult)?.forEach {
                    resultSearch.add(it)
                }
                runOnUiThread {
                    setOotRecycledView()

                }
            }.start()


        }


    }


    private fun setOotRecycledView() {

        val recyclerView: RecyclerView = binding.itemSearchMovie
        val goIDMovie: TheMDBRepoUseCaseSync by lazy { app.theMDBRepoUseCaseSync }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val stateClickListener: MovieAdapter.OnStateClickListener =
            object : MovieAdapter.OnStateClickListener {
                override fun onStateClick(movie: ResultSearchMovie, position: Int) {

                    Thread {
                        val iDMovie = goIDMovie.getReposForIDMovieSync(movie)
                        runOnUiThread {
                            val intent = Intent(this@SearchActivity, FilmActivity::class.java)
                            intent.putExtra(Movie::class.java.simpleName, iDMovie)

                            startActivity(intent)
                        }


                    }.start()


                }
            }


        recyclerView.adapter = MovieAdapter(resultSearch, stateClickListener)


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