package com.edurda77.filmlibrary.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.edurda77.filmlibrary.databinding.ActivitySearchBinding
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    val theMdbUrl: String = "https://api.themoviedb.org/3/search/movie?api_key=" +
            "2513408bca2d22ed908b2b3badf57939&language=ru-RU&query=" +
            "%D1%81%D1%82%D1%80%D0%B0%D0%B6%D0%B8%20%D0%B3%D0%B0%D0%BB%D0%B0%D0%BA%D1%82%D0%B8%D0%BA%D0%B8&" +
            "page=1&include_adult=false"
    поиск
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.goSearchMovie.setOnClickListener {
            Thread {
                var urlConnection: HttpsURLConnection? = null
                try {
                    val url = URL(theMdbUrl)
                    urlConnection = url.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.connectTimeout = 5_000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val result = bufferedReader.readLine().toString()
                    runOnUiThread {
                        binding.resultSearchView.text = result
                    }
                } catch (e: Exception) {
                    Log.e("", "Соединение отсутствует", e)
                    e.printStackTrace()
                } finally {
                    urlConnection?.disconnect()
                }
            }.start()

        }
    }

}