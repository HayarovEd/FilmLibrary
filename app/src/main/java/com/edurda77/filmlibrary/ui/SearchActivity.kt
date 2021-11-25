package com.edurda77.filmlibrary.ui

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultSearchMovie
import com.edurda77.filmlibrary.databinding.ActivitySearchBinding
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val goSearchMovie: TheMDBRepoUseCace by lazy { app.theMDBRepoUseCace }
    private val resultSearch = emptyList<ResultSearchMovie>().toMutableList()



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val adult : Boolean = preferences.getBoolean("SetAdult", false)

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


}