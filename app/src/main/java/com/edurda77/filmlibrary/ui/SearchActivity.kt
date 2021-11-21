package com.edurda77.filmlibrary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edurda77.filmlibrary.data.ResultSearchMovie
import com.edurda77.filmlibrary.databinding.ActivitySearchBinding

import android.content.Intent

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val goSearchMovie: TheMDBRepoUseCace by lazy { app.theMDBRepoUseCace }
    val resultSearch = emptyList<ResultSearchMovie>().toMutableList()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goSearchMovie.setOnClickListener {
            resultSearch.clear()
            val searchString = binding.searchMovie.text.toString()
            Thread {
                val repos = goSearchMovie.getReposForUserSync(searchString)
                repos.forEach {
                    resultSearch.add(it)
                }
                runOnUiThread {
                    setOotRecycledView()
                    //binding.resultSearchView.text = sb.toString()
                }
            }.start()


        }


    }


    fun setOotRecycledView() {

        val recyclerView: RecyclerView = binding.itemSearchMovie
        val goIDMovie: TheMDBRepoUseCace by lazy { app.theMDBRepoSearchMovieCac }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val stateClickListener: MovieSearchAdapter.OnStateClickListener =
            object : MovieSearchAdapter.OnStateClickListener {
                override fun onStateClick(movie: ResultSearchMovie, position: Int) {

                    Thread {
                        val iDMovie  = goIDMovie.getReposForSearcheMovieSync(movie)
                        runOnUiThread {
                            val intent = Intent(this@SearchActivity, FilmActivity::class.java)
                            intent.putExtra(Movie::class.java.getSimpleName(), iDMovie)

                            startActivity(intent)
                        }


                    }.start()


                }
            }


        recyclerView.adapter = MovieSearchAdapter(resultSearch, stateClickListener)


    }


}