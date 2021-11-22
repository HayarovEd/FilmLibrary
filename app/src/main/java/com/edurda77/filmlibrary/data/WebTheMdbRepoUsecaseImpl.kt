package com.edurda77.filmlibrary.data

import com.edurda77.filmlibrary.BuildConfig
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WebTheMdbRepoUsecaseImpl : TheMDBRepoUseCace {
    override fun getReposForSearchMovieSync(userName: String): List<ResultSearchMovie> {
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

            resJson.results.forEach {
                resultSearch.add(it)


            }


        } finally {
            urlConnection?.disconnect()
        }
        return resultSearch
    }

    override fun getReposForSearchMovieAsync(
        userName: String,
        callback: (List<ResultSearchMovie>) -> Unit
    ) {
        Thread {
            callback.invoke(getReposForSearchMovieSync(userName))
        }.start()
    }

    override fun getReposForIDMovieSync(searcheMovie: ResultSearchMovie): Movie {
        val gson by lazy { Gson() }
        fun getUrl() = URL(
            "https://api.themoviedb.org/3/movie/" + searcheMovie.id +
                    "?api_key=${BuildConfig.TMDB_API_KEY}&language=ru-RU"
        )
        var urlConnection: HttpsURLConnection? = null
        try {
            urlConnection = getUrl().openConnection() as HttpsURLConnection
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

    override fun getReposForIDMovieAsync(
        searcheMovie: ResultSearchMovie,
        callback: (Movie) -> Unit
    ) {
        Thread {
            callback.invoke(getReposForIDMovieSync(searcheMovie))
        }.start()
    }

    override fun getReposForGenresSync(): List<Genres> {
        TODO("Not yet implemented")
    }

    override fun getReposForGenresAsync(callback: (List<Genres>) -> Unit) {
        TODO("Not yet implemented")
    }


}