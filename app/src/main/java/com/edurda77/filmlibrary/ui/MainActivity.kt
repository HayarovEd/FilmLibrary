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
import com.edurda77.filmlibrary.data.FilmGenre
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultSearchMovie
import com.edurda77.filmlibrary.databinding.ActivityMainBinding
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace


private var toolbar: Toolbar? = null



class MainActivity : AppCompatActivity() {
    private val goNowPlayingMovie: TheMDBRepoUseCace by lazy { app.theMDBRepoUseCace }
    val resultNowPlayingMovie = emptyList<ResultSearchMovie>().toMutableList()
    val resultPopularMovie = emptyList<ResultSearchMovie>().toMutableList()
    val resultTopRatedMovie = emptyList<ResultSearchMovie>().toMutableList()
    val resultUpcomingMovie = emptyList<ResultSearchMovie>().toMutableList()

    //private val goGenres: TheMDBRepoUseCace by lazy { app.theMDBRepoUseCace }
    private var action = listOf(
        Movie(15, "Терминатор", "action", 120, 10.0, "1984", 1, 3, "fgfgfgfgfg", ""),
        Movie(16, "Терминатор2", "action", 120, 10.0, "1992", 1, 10, "fgfgfgfgfg", ""),
    )
    private var camedy = listOf(
        Movie(17, "Амерканский пирог", "action", 120, 10.0, "1984", 1, 3, "fgfgfgfgfg", ""),
        Movie(18, "Американский пирог2", "action", 120, 10.0, "1992", 1, 10, "fgfgfgfgfg", ""),
    )
    private var triller = listOf(
        Movie(19, "Цвет ночи", "action", 120, 10.0, "1984", 1, 3, "fgfgfgfgfg", ""),
        Movie(20, "Семь", "action", 120, 10.0, "1992", 1, 10, "fgfgfgfgfg", ""),
    )
    private var ganre = emptyList<FilmGenre>().toMutableList()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setToolbar()







        Thread {
            val reposNowPlaying = goNowPlayingMovie.getReposForNowPlayingMovieSync()
            if (reposNowPlaying != null) {
                reposNowPlaying.forEach {
                    resultNowPlayingMovie.add(it)
                }
            }
            ganre.add(FilmGenre("Сейчас в кинотеатрах",resultNowPlayingMovie) )
            val reposPopularMovie = goNowPlayingMovie.getReposForPopularMovieSync()
            if (reposPopularMovie != null) {
                reposPopularMovie.forEach {
                    resultPopularMovie.add(it)
                }
            }
            ganre.add(FilmGenre("Популярные фильмы",resultPopularMovie) )
            /*runOnUiThread {
                setOotRecycledView()

            }*/
        }.start()
        setOotRecycledView()

    }

    fun setToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

    }
    fun getGroupMovies () {

    }
    fun setOotRecycledView() {

        val recyclerView: RecyclerView = binding.outRecycledView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val stateClickListener: MovieAdapter.OnStateClickListener =
            object : MovieAdapter.OnStateClickListener {
                override fun onStateClick(movie: Movie, position: Int) {

                    val intent = Intent(this@MainActivity, FilmActivity::class.java)
                    intent.putExtra(Movie::class.java.getSimpleName(), movie)
                    startActivity(intent)
                }
            }


        recyclerView.adapter = OutAdapter(ganre, stateClickListener)


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.custom -> {
                val intent = Intent(this, SearchActivity::class.java)
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

