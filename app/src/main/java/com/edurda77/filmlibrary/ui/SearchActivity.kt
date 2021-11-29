package com.edurda77.filmlibrary.ui

import android.content.Context
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
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace

private const val DEFAUL_KEY = "DEFAUL_KEY"
class SearchActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var binding: ActivitySearchBinding
    private val goSearchMovie: TheMDBRepoUseCace by lazy { app.theMDBRepoUseCace }
    private val resultSearch = emptyList<ResultSearchMovie>().toMutableList()
    private val prefernces: SharedPreferences by lazy {app.sharedPrefernces}



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        val adult= prefernces.getBoolean(DEFAUL_KEY,false)

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


    fun setOotRecycledView() {

        val recyclerView: RecyclerView = binding.itemSearchMovie
        val goIDMovie: TheMDBRepoUseCace by lazy { app.theMDBRepoUseCace }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val stateClickListener: MovieSearchAdapter.OnStateClickListener =
            object : MovieSearchAdapter.OnStateClickListener {
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


        recyclerView.adapter = MovieSearchAdapter(resultSearch, stateClickListener)


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

}