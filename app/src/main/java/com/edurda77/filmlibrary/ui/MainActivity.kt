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


    private var ganre = emptyList<FilmGenre>().toMutableList()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setToolbar()
        getGroupMovies ()

    }

    private fun setToolbar() {
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

        val stateClickListener: MovieSearchAdapter.OnStateClickListener =
            object : MovieSearchAdapter.OnStateClickListener {
                override fun onStateClick(movie: ResultSearchMovie, position: Int) {
                    Thread {
                        val iDMovie = goIDMovie.getReposForIDMovieSync(movie)

                        runOnUiThread {
                            val intent = Intent(this@MainActivity, FilmActivity::class.java)
                            intent.putExtra(Movie::class.java.simpleName, iDMovie)

                            startActivity(intent)
                        }
                    }.start()
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

