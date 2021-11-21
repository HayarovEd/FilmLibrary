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

    override fun getReposForSearcheMovieSync(searcheMovie: ResultSearchMovie): Movie {
        val gson by lazy { Gson() }

        var urlConnection: HttpsURLConnection? = null
        try {
            urlConnection =
                URL("https://api.themoviedb.org/3/movie/" + searcheMovie.id + "?api_key=${BuildConfig.TMDB_API_KEY}&language=ru-RU")
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
            val moviePopularity = resJson.popularity
            val movieBudget = resJson.budget
            val movieRevenue = resJson.revenue
            val movieOverview = resJson.overview
            val movieGanre = "released last"
            val movie = Movie(
                movieId, movieTitle, movieGanre, movieRuntime,
                moviePopularity, movieReleaseDate, movieBudget, movieRevenue, movieOverview
            )
            return movie
        } finally {
            urlConnection?.disconnect()
        }
    }

    override fun getReposForSearcheMovieAsync(
        searcheMovie: ResultSearchMovie,
        callback: (Movie) -> Unit
    ) {
        Thread {
            callback.invoke(getReposForSearcheMovieSync(searcheMovie))
        }.start()
    }


}