package com.edurda77.filmlibrary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edurda77.filmlibrary.BuildConfig.TMDB_API_KEY
import com.edurda77.filmlibrary.data.ResultSearchMovie
import com.edurda77.filmlibrary.data.ResultsParsing
import com.edurda77.filmlibrary.databinding.ActivitySearchBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import android.net.NetworkInfo

import android.net.ConnectivityManager

import android.content.Intent

import android.content.BroadcastReceiver
import android.content.Context
import android.widget.Toast

import android.content.IntentFilter
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
            Thread{
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

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val stateClickListener: MovieSearchAdapter.OnStateClickListener =
            object : MovieSearchAdapter.OnStateClickListener {
                override fun onStateClick(movie: ResultSearchMovie, position: Int) {
                    val gson by lazy { Gson() }
                    Thread {
                        var urlConnection: HttpsURLConnection? = null
                        try {
                            urlConnection =
                                URL("https://api.themoviedb.org/3/movie/" + movie.id + "?api_key=$TMDB_API_KEY&language=ru-RU")
                                    .openConnection() as HttpsURLConnection
                            urlConnection.requestMethod = "GET"
                            urlConnection.connectTimeout = 5_000
                            val bufferedReader =
                                BufferedReader(InputStreamReader(urlConnection.inputStream))
                            val result = bufferedReader.readLine().toString()
                            val resJson = gson.fromJson(result, Movie::class.java)
                            val movieId = resJson.id
                            val movieTitle = resJson.title
                            val movieRuntime = resJson.runtime
                            val movieReleaseDate = resJson.release_date
                            val movieBudget = resJson.budget
                            val movieRevenue = resJson.revenue
                            val movieOverview = resJson.overview
                            val movieGanre = "released last"
                            Toast.makeText(this@SearchActivity, resJson.title, Toast.LENGTH_LONG).show()
                            runOnUiThread {
                                val intent = Intent(this@SearchActivity, FilmActivity::class.java)
                                intent.putExtra("id", movieId)
                                intent.putExtra("title", movieTitle)
                                intent.putExtra("runtime", movieRuntime)
                                intent.putExtra("releaseDate", movieReleaseDate)
                                intent.putExtra("budget", movieBudget)
                                intent.putExtra("revenue", movieRevenue)
                                intent.putExtra("overview", movieOverview)
                                intent.putExtra("ganre", movieGanre)
                            }
                        } catch (e: Exception) {
                            Snackbar.make(
                                binding.root,
                                "Неудачная загрузка",
                                Snackbar.LENGTH_LONG
                            ).show()
                        } finally {
                            urlConnection?.disconnect()
                        }
                    }.start()


                }
            }


        recyclerView.adapter = MovieSearchAdapter(resultSearch, stateClickListener)


    }


}