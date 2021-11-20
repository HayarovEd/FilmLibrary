package com.edurda77.filmlibrary.data

import androidx.core.graphics.scaleMatrix
import com.edurda77.filmlibrary.BuildConfig
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WebTheMdbRepoUsecaseImpl : TheMDBRepoUseCace {
    override fun getReposForUserSync(userName: String): List<ResultSearchMovie> {
        val gson by lazy { Gson() }

        fun getUrl(search: String) = URL(
            "https://api.themoviedb.org/3/search/movie?api_key=${BuildConfig.TMDB_API_KEY}&language=ru-RU&query=$search"
        )
        val resultSearch = emptyList<ResultSearchMovie>().toMutableList()
        var urlConnection: HttpsURLConnection? = null
        try {
            urlConnection = getUrl(userName).openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connectTimeout = 5_000
            val bufferedReader =
                BufferedReader(InputStreamReader(urlConnection.inputStream))
            val result = bufferedReader.readLine().toString()
            val resJson = gson.fromJson(result, ResultsParsing::class.java)
            //val sb = StringBuilder()
            resJson.results.forEach {
                resultSearch.add(it)

                //sb.appendLine("ID " + it.id.toString() + "  Название: " + it.title + "  Краткое содаржание: " + it.overview)
            }


        } finally {
            urlConnection?.disconnect()
        }
        return resultSearch
    }

    override fun getReposForUserAsync(
        userName: String,
        callback: (List<ResultSearchMovie>) -> Unit
    ) {
        Thread {
            callback.invoke(getReposForUserSync(userName))
        }.start()
    }


}