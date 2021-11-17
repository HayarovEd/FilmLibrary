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
import java.lang.StringBuilder
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



class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val gson by lazy { Gson() }
    private val resultSearch = emptyList<ResultSearchMovie>().toMutableList()
    private fun getUrl(search: String) = URL(
        "https://api.themoviedb.org/3/search/movie?api_key=$TMDB_API_KEY&language=ru-RU&query=$search"
    )

    private val networkStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = manager.activeNetworkInfo
            if (ni != null) {
                doOnNetworkChange(context, ni)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goSearchMovie.setOnClickListener {
            toStartService("Начало поиска")
            resultSearch.clear()
            val searchString = binding.searchMovie.text.toString()
            Thread {
                var urlConnection: HttpsURLConnection? = null
                try {
                    urlConnection = getUrl(searchString).openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.connectTimeout = 5_000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val result = bufferedReader.readLine().toString()
                    val resJson = gson.fromJson(result, ResultsParsing::class.java)
                    val sb = StringBuilder()
                    resJson.results.forEach {
                        resultSearch.add(it)
                        sb.appendLine("ID " + it.id.toString() + "  Название: " + it.title + "  Краткое содаржание: " + it.overview)
                    }

                    runOnUiThread {
                        binding.resultSearchView.text = sb.toString()
                    }
                } catch (e: Exception) {
                    Snackbar.make(
                        binding.root,
                        "Ни чего не найдено, попробуйте снова",
                        Snackbar.LENGTH_LONG
                    ).show()
                } finally {
                    urlConnection?.disconnect()
                }
            }.start()
            toStartService("конец поиска")
        }
        setOotRecycledView()

    }

    override fun onResume() {
        super.onResume()
        toStartService("работа продолжена")
        registerReceiver(
            networkStateReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onPause() {
        toStartService("работа приостановлена")
        unregisterReceiver(networkStateReceiver)
        super.onPause()
    }

    fun doOnNetworkChange(context: Context?, ni: NetworkInfo) {
        println(ni)
        Toast.makeText(context, ni.toString(), Toast.LENGTH_LONG).show()
    }

    fun toStartService(string: String) {
        val intentMyIntentService = Intent(this, LogEvent::class.java)
        startService(
            intentMyIntentService.putExtra(
                "task",
                string
            )
        )
    }

    fun setOotRecycledView() {

        val recyclerView: RecyclerView = binding.itemSearchMovie

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val stateClickListener: MovieSearchAdapter.OnStateClickListener =
            object : MovieSearchAdapter.OnStateClickListener {
                override fun onStateClick(movie: ResultSearchMovie, position: Int) {
                    toStartService("Начало загрузки фильма")
                    Thread {
                        var urlConnection: HttpsURLConnection? = null
                        try {
                            urlConnection = getUrl("https://api.themoviedb.org/3/movie/{"+movie.id +"}?api_key=<<"+TMDB_API_KEY+">>&language=ru-RU").openConnection() as HttpsURLConnection
                            urlConnection.requestMethod = "GET"
                            urlConnection.connectTimeout = 5_000
                            val bufferedReader =
                                BufferedReader(InputStreamReader(urlConnection.inputStream))
                            val result = bufferedReader.readLine().toString()
                            /*val resJson = gson.fromJson(result, ResultsParsing::class.java)
                            val sb = StringBuilder()
                            resJson.results.forEach {
                                resultSearch.add(it)
                                sb.appendLine("ID " + it.id.toString() + "  Название: " + it.title + "  Краткое содаржание: " + it.overview)
                            }

                            runOnUiThread {
                                binding.resultSearchView.text = sb.toString()
                            }*/
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
                    toStartService("конец загрузки фильма")

                }
            }


        recyclerView.adapter = MovieSearchAdapter(resultSearch, stateClickListener)


    }

}