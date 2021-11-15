package com.edurda77.filmlibrary.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.edurda77.filmlibrary.BuildConfig.TMDB_API_KEY
import com.edurda77.filmlibrary.data.ResultSearсhMovies
import com.edurda77.filmlibrary.data.ResultsParsing
import com.edurda77.filmlibrary.databinding.ActivitySearchBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.lang.StringBuilder
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val gson by lazy { Gson() }

    private fun getUrl(search: String) =  URL (
         "https://api.themoviedb.org/3/search/movie?api_key="+TMDB_API_KEY+"&language=ru-RU&query="+search
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.goSearchMovie.setOnClickListener {
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
                        sb.appendLine(it.toString())
                    }
                    runOnUiThread {
                        binding.resultSearchView.text = sb.toString()
                    }
                } catch (e: Exception) {
                    Snackbar.make(binding.root, "Ни чего не найдено, попробуйте снова", Snackbar.LENGTH_LONG).show()
                } finally {
                    urlConnection?.disconnect()
                }
            }.start()

        }
    }

}