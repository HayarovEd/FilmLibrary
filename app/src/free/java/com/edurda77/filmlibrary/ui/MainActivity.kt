package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import android.content.Intent
import android.widget.Toast
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
    //private var toolbar: Toolbar? = null
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

        getGroupMovies ()

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

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val stateClickListener: MovieAdapter.OnStateClickListener =
            object : MovieAdapter.OnStateClickListener {
                override fun onStateClick(movie: ResultSearchMovie, position: Int) {
                    Thread {


                        runOnUiThread {

                            Toast.makeText(this@MainActivity,"Только в платной версии)))",Toast.LENGTH_LONG).show()
                        }
                    }.start()
                }
            }


        recyclerView.adapter = OutAdapter(genre, stateClickListener)


    }





}

