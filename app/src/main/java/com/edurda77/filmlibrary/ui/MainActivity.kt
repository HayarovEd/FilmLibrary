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





class MainActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private val goNowPlayingMovie: TheMDBRepoUseCace by lazy { app.theMDBRepoUseCace }
    private val resultNowPlayingMovie = emptyList<ResultSearchMovie>().toMutableList()
    private val resultPopularMovie = emptyList<ResultSearchMovie>().toMutableList()
    private val resultTopRatedMovie = emptyList<ResultSearchMovie>().toMutableList()
    private val resultUpcomingMovie = emptyList<ResultSearchMovie>().toMutableList()

    //private val goGenres: TheMDBRepoUseCace by lazy { app.theMDBRepoUseCace }
    /*private var action = listOf(
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
    )*/
    private var ganre = emptyList<FilmGenre>().toMutableList()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setToolbar()

        getGroupMovies ()






        //setOotRecycledView()

    }

    fun setToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

    }
    fun getGroupMovies () {
        Thread {
            goNowPlayingMovie.getReposForNowPlayingMovieSync()?.forEach {
                resultNowPlayingMovie.add(it)
            }
            ganre.add(FilmGenre("Сейчас в кинотеатрах", resultNowPlayingMovie))
            goNowPlayingMovie.getReposForPopularMovieSync()?.forEach {
                resultPopularMovie.add(it)
            }
            ganre.add(FilmGenre("Популярные фильмы", resultPopularMovie))
            goNowPlayingMovie.getReposForTopRatedMovieSync()?.forEach {
                resultTopRatedMovie.add(it)
            }
            ganre.add(FilmGenre("Высокий рейтинг", resultTopRatedMovie))
            goNowPlayingMovie.getReposForUpcomingMovieSync()?.forEach {
                resultUpcomingMovie.add(it)
            }
            ganre.add(FilmGenre("Скоро в прокате", resultUpcomingMovie))
            runOnUiThread {
                setOotRecycledView()

            }
        }.start()
    }
    fun setOotRecycledView() {

        val recyclerView: RecyclerView = binding.outRecycledView
        val goIDMovie: TheMDBRepoUseCace by lazy { app.theMDBRepoUseCace }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val stateClickListener: MovieAdapter.OnStateClickListener =
            object : MovieAdapter.OnStateClickListener {
                override fun onStateClick(movie: ResultSearchMovie, position: Int) {
                    Thread {
                        val iDMovie = goIDMovie.getReposForIDMovieSync(movie)
                        runOnUiThread {
                            val intent = Intent(this@MainActivity, FilmActivity::class.java)
                            intent.putExtra(Movie::class.java.simpleName, iDMovie)

                            startActivity(intent)
                        }
                    }
                }
            }


        recyclerView.adapter = OutAdapter(ganre, stateClickListener)


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

