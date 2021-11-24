package com.edurda77.filmlibrary.data

import com.edurda77.filmlibrary.BuildConfig.TMDB_API_KEY
import com.edurda77.filmlibrary.data.retrofit.TheMDBRepoApi
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException


private const val BASE_URL = "https://api.themoviedb.org/3/"
const val language = "ru-RU"
const val apiKey = TMDB_API_KEY
val includeAdult=false

class RetrofitTheMdbRepoUsecaseImpl : TheMDBRepoUseCace {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var api: TheMDBRepoApi = retrofit.create(TheMDBRepoApi::class.java)


    override fun getReposForSearchMovieSync(userName: String): List<ResultSearchMovie> {

        val resultsParsing: ResultsParsing? = api.getSearchMovie(apiKey, language, userName,includeAdult)
            .execute().body()
        return parsingForSync(resultsParsing)

    }


    override fun getReposForIDMovieSync(searcheMovie: ResultSearchMovie): Movie? {

        return api.getIDMovie(searcheMovie.id, apiKey, language)
            .execute().body()
    }


    override fun getReposForGenresSync(): List<Genres> {

        return api.getGenres(apiKey).execute().body() ?: emptyList()

    }

    override fun getReposForNowPlayingMovieSync(): List<ResultSearchMovie>? {
        val resultsParsing: ResultsParsing? = api.getNowPlaying(apiKey, language)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForPopularMovieSync(): List<ResultSearchMovie>? {
        val resultsParsing: ResultsParsing? = api.getPopular(apiKey, language)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForTopRatedMovieSync(): List<ResultSearchMovie>? {
        val resultsParsing: ResultsParsing? = api.getTopRated(apiKey, language)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForUpcomingMovieSync(): List<ResultSearchMovie>? {
        val resultsParsing: ResultsParsing? = api.getUpcoming(apiKey, language)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForSearchMovieAsync(
        userName: String,
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getSearchMovie(apiKey, language, userName, includeAdult)
            .enqueue(object : Callback<ResultsParsing> {
            override fun onResponse(
                call: Call<ResultsParsing>,
                response: Response<ResultsParsing>
            ) {
                if (response.isSuccessful) {
                    onSuccess(
                        response.body()?.results ?: throw IllegalStateException("Нулевой результат")
                    )
                } else {
                    OnError(Throwable("Неизвестная ошибка"))
                }

            }

            override fun onFailure(call: Call<ResultsParsing>, t: Throwable) {
                OnError(t)
            }


        })
    }

    override fun getReposForIDMovieAsync(
        searcheMovie: ResultSearchMovie,
        onSuccess: (Movie) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getIDMovie(searcheMovie.id, apiKey, language).enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                if (response.isSuccessful) {
                    onSuccess(response.body()!!)
                } else {
                    OnError(Throwable("Неизвестная ошибка"))
                }

            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                OnError(t)
            }


        })
    }

    override fun getReposForGenresAsync(
        onSuccess: (List<Genres>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getGenres(apiKey).enqueue(object : Callback<List<Genres>> {
            override fun onResponse(call: Call<List<Genres>>, response: Response<List<Genres>>) {
                if (response.isSuccessful) {
                    onSuccess(response.body() ?: throw IllegalStateException("Нулевой результат"))
                } else {
                    OnError(Throwable("Неизвестная ошибка"))
                }

            }

            override fun onFailure(call: Call<List<Genres>>, t: Throwable) {
                OnError(t)
            }
        })
    }

    override fun getReposForNowPlayingMovieAsync(
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getNowPlaying(apiKey, language).enqueue(object : Callback<ResultsParsing> {
            override fun onResponse(
                call: Call<ResultsParsing>,
                response: Response<ResultsParsing>
            ) {
                if (response.isSuccessful) {
                    onSuccess(
                        response.body()?.results ?: throw IllegalStateException("Нулевой результат")
                    )
                } else {
                    OnError(Throwable("Неизвестная ошибка"))
                }

            }

            override fun onFailure(call: Call<ResultsParsing>, t: Throwable) {
                OnError(t)
            }


        })
    }

    override fun getReposForPopularMovieAsync(
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getPopular(apiKey, language).enqueue(object : Callback<ResultsParsing> {
            override fun onResponse(
                call: Call<ResultsParsing>,
                response: Response<ResultsParsing>
            ) {
                if (response.isSuccessful) {
                    onSuccess(
                        response.body()?.results ?: throw IllegalStateException("Нулевой результат")
                    )
                } else {
                    OnError(Throwable("Неизвестная ошибка"))
                }

            }

            override fun onFailure(call: Call<ResultsParsing>, t: Throwable) {
                OnError(t)
            }


        })
    }

    override fun getReposForTopRatedMovieAsync(
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getTopRated(apiKey, language).enqueue(object : Callback<ResultsParsing> {
            override fun onResponse(
                call: Call<ResultsParsing>,
                response: Response<ResultsParsing>
            ) {
                if (response.isSuccessful) {
                    onSuccess(
                        response.body()?.results ?: throw IllegalStateException("Нулевой результат")
                    )
                } else {
                    OnError(Throwable("Неизвестная ошибка"))
                }

            }

            override fun onFailure(call: Call<ResultsParsing>, t: Throwable) {
                OnError(t)
            }


        })
    }

    override fun getReposForUpcomingMovieAsync(
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getUpcoming(apiKey, language).enqueue(object : Callback<ResultsParsing> {
            override fun onResponse(
                call: Call<ResultsParsing>,
                response: Response<ResultsParsing>
            ) {
                if (response.isSuccessful) {
                    onSuccess(
                        response.body()?.results ?: throw IllegalStateException("Нулевой результат")
                    )
                } else {
                    OnError(Throwable("Неизвестная ошибка"))
                }

            }

            override fun onFailure(call: Call<ResultsParsing>, t: Throwable) {
                OnError(t)
            }


        })
    }

    fun parsingForSync(resultsParsing: ResultsParsing?): List<ResultSearchMovie> {
        val resultSearch = emptyList<ResultSearchMovie>().toMutableList()

        if (resultsParsing != null) {
            resultsParsing.results.forEach {
                resultSearch.add(it)
            }
        }
        return resultSearch
    }
}