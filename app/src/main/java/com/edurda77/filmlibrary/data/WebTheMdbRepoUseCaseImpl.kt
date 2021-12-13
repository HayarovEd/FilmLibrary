package com.edurda77.filmlibrary.data

import com.edurda77.filmlibrary.BuildConfig
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCaseAsync
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCaseSync
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WebTheMdbRepoUseCaseImpl : TheMDBRepoUseCaseSync, TheMDBRepoUseCaseAsync {
    override fun getReposForSearchMovieSync(userName: String, adultKey:Boolean): List<ResultSearchMovie> {
        val gson by lazy { Gson() }


        fun getUrl(search: String) = URL(
            "https://api.themoviedb.org/3/search/movie?api_key=${TMDB_API_KEY}&language=ru-RU&query=$search"
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


        } catch (thr: Throwable) {
            urlConnection?.disconnect()
            throw thr
        } finally {
            urlConnection?.disconnect()
        }
        return resultSearch
    }

    override fun getReposForSearchMovieAsync(
        userName: String,
        adultKey:Boolean,
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        Thread {
            try {
                val res = getReposForSearchMovieSync(userName, adultKey)
                onSuccess.invoke(res)
            } catch (thr: Throwable) {
                OnError (thr)
            }

        }.start()
    }

    override fun getReposForIDMovieSync(searcheMovie: ResultSearchMovie): Movie {
        val gson by lazy { Gson() }
        fun getUrl() = URL(
            "https://api.themoviedb.org/3/movie/" + searcheMovie.id +
                    "?api_key=${TMDB_API_KEY}&language=ru-RU"
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
            val movieReleaseDate = resJson.releaseDate
            val moviePopularity = resJson.popularity
            val movieBudget = resJson.budget
            val movieRevenue = resJson.revenue
            val movieOverview = resJson.overview
            val movieGenre = resJson.movieGenre
            val moviePathPicture = resJson.posterPath
            return Movie(
                movieId,
                movieTitle,
                movieGenre,
                movieRuntime,
                moviePopularity,
                movieReleaseDate,
                movieBudget,
                movieRevenue,
                movieOverview,
                moviePathPicture
            )
        } catch (thr: Throwable) {
            urlConnection?.disconnect()
            throw thr
        } finally {
            urlConnection?.disconnect()
        }
    }

    override fun getReposForIDMovieAsync(
        searcheMovie: ResultSearchMovie,
        onSuccess: (Movie) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        Thread {
            try {
                val res = getReposForIDMovieSync(searcheMovie)
                onSuccess.invoke(res)
            } catch (thr: Throwable) {
                OnError (thr)
            }
        }.start()
    }

    override fun getReposForGenresSync(): List<Genres> {
        TODO("Not yet implemented")
    }

    override fun getReposForNowPlayingMovieSync(): List<ResultSearchMovie>? {
        TODO("Not yet implemented")
    }

    override fun getReposForPopularMovieSync(): List<ResultSearchMovie>? {
        TODO("Not yet implemented")
    }

    override fun getReposForTopRatedMovieSync(): List<ResultSearchMovie>? {
        TODO("Not yet implemented")
    }

    override fun getReposForUpcomingMovieSync(): List<ResultSearchMovie>? {
        TODO("Not yet implemented")
    }

    override fun getReposForSearchPeopleSync(name: String): List<ResultSearchedPeople>? {
        TODO("Not yet implemented")
    }

    override fun getReposForIdPeopleSync(searchedPeople: ResultSearchedPeople): People? {
        TODO("Not yet implemented")
    }

    override fun getReposForGenresAsync(
        onSuccess: (List<Genres>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getReposForNowPlayingMovieAsync(
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getReposForPopularMovieAsync(
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getReposForTopRatedMovieAsync(
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getReposForUpcomingMovieAsync(
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getReposForSearchPeopleAsync(
        name: String,
        onSuccess: (List<ResultSearchedPeople>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getReposForIdPeopleAsync(
        searchedPeople: ResultSearchedPeople,
        onSuccess: (People) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }


}