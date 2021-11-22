package com.edurda77.filmlibrary.data

import com.edurda77.filmlibrary.BuildConfig.TMDB_API_KEY
import com.edurda77.filmlibrary.data.retrofit.TheMDBRepoApi
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.themoviedb.org/3/"
val language = "ru-RU"
val apiKey = TMDB_API_KEY

class RetrofitTheMdbRepoUsecaseImpl : TheMDBRepoUseCace {
    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var api: TheMDBRepoApi = retrofit.create(TheMDBRepoApi::class.java)


    override fun getReposForSearchMovieSync(userName: String): List<ResultSearchMovie> {
        val resultsParsing : ResultsParsing? = api.getSearchMovie(apiKey, language,userName)
            .execute().body()

        val resultSearch = emptyList<ResultSearchMovie>().toMutableList()

        if (resultsParsing != null) {
            resultsParsing.results.forEach {
                resultSearch.add(it)

            }
        }

         return resultSearch
    }

    override fun getReposForSearchMovieAsync(
        userName: String,
        callback: (List<ResultSearchMovie>) -> Unit
    ) {
        api.getSearchMovie(userName,apiKey, language).enqueue(object : Callback<ResultsParsing>{
            override fun onResponse(
                call: Call<ResultsParsing>,
                response: Response<ResultsParsing>
            ) {

               // callback(response.body() ?: emptyList())
            }

            override fun onFailure(call: Call<ResultsParsing>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }

    override fun getReposForIDMovieSync(searcheMovie: ResultSearchMovie): Movie? {

        return api.getIDMovie(searcheMovie.id, apiKey, language)
            .execute().body()
    }

    override fun getReposForIDMovieAsync(
        searcheMovie: ResultSearchMovie,
        callback: (Movie) -> Unit
    ) {
        api.getIDMovie(searcheMovie.id, apiKey, language).enqueue(object : Callback<Movie>{
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                callback(response.body()!!)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }

    override fun getReposForGenresSync(): List<Genres> {

        return  api.getGenres(apiKey).execute().body()?: emptyList()

    }

    override fun getReposForGenresAsync(callback: (List<Genres>) -> Unit) {
        api.getGenres(apiKey).enqueue (object : Callback<List<Genres>>{
            override fun onResponse(call: Call<List<Genres>>, response: Response<List<Genres>>) {
               callback(response.body()?: emptyList())
            }

            override fun onFailure(call: Call<List<Genres>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    /*override fun getReposForSearchMovieSync(userName: String): List<ResultSearchMovie> {
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
    }*/


}