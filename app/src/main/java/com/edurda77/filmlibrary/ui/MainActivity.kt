package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
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
import com.edurda77.filmlibrary.domain.AlarmUpcomingMovieCase
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCaseSync

import java.util.*


class MainActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private val goNowPlayingMovie: TheMDBRepoUseCaseSync by lazy { app.theMDBRepoUseCaseSync }
    private val goReleaseMovieToday: AlarmUpcomingMovieCase by lazy {app.alarmUpcomingMovieCase}
    private val resultNowPlayingMovie = emptyList<ResultSearchMovie>().toMutableList()
    private val resultPopularMovie = emptyList<ResultSearchMovie>().toMutableList()
    private val resultTopRatedMovie = emptyList<ResultSearchMovie>().toMutableList()
    private val resultUpcomingMovie = emptyList<ResultSearchMovie>().toMutableList()


    private var genre = emptyList<FilmGenre>().toMutableList()
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
    @SuppressLint("SimpleDateFormat")
    private fun getGroupMovies () {
        Thread {
            goNowPlayingMovie.getReposForNowPlayingMovieSync()?.forEach {
                resultNowPlayingMovie.add(it)
            }
            genre.add(FilmGenre("Сейчас в кинотеатрах", resultNowPlayingMovie))
            goNowPlayingMovie.getReposForPopularMovieSync()?.forEach {
                resultPopularMovie.add(it)
            }
            genre.add(FilmGenre("Популярные фильмы", resultPopularMovie))
            goNowPlayingMovie.getReposForTopRatedMovieSync()?.forEach {
                resultTopRatedMovie.add(it)
            }
            genre.add(FilmGenre("Высокий рейтинг", resultTopRatedMovie))
            goNowPlayingMovie.getReposForUpcomingMovieSync()?.forEach {
                resultUpcomingMovie.add(it)
            }
            resultUpcomingMovie.forEach{
                goReleaseMovieToday.setAlarm(it,this)
            }
            genre.add(FilmGenre("Скоро в прокате", resultUpcomingMovie))
            runOnUiThread {
                setOotRecycledView()

            }
        }.start()
    }
    private fun setOotRecycledView() {

        val recyclerView: RecyclerView = binding.outRecycledView
        val goIDMovie: TheMDBRepoUseCaseSync by lazy { app.theMDBRepoUseCaseSync }
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
                    }.start()
                }
            }


        recyclerView.adapter = OutAdapter(genre, stateClickListener)


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

