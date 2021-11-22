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
val language = "ru-RU"
val apiKey = TMDB_API_KEY

class RetrofitTheMdbRepoUsecaseImpl : TheMDBRepoUseCace {
    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var api: TheMDBRepoApi = retrofit.create(TheMDBRepoApi::class.java)


    override fun getReposForSearchMovieSync(userName: String): List<ResultSearchMovie> {
        val resultsParsing: ResultsParsing? = api.getSearchMovie(apiKey, language, userName)
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
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getSearchMovie(userName, apiKey, language).enqueue(object : Callback<ResultsParsing> {
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

    override fun getReposForIDMovieSync(searcheMovie: ResultSearchMovie): Movie? {

        return api.getIDMovie(searcheMovie.id, apiKey, language)
            .execute().body()
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

    override fun getReposForGenresSync(): List<Genres> {

        return api.getGenres(apiKey).execute().body() ?: emptyList()

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


}