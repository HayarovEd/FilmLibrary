package com.edurda77.filmlibrary.data

import com.edurda77.filmlibrary.BuildConfig
import com.edurda77.filmlibrary.data.retrofit.TheMDBRepoApi
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCaseAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
const val TMDB_API_KEY="2513408bca2d22ed908b2b3badf57939"
class RetrofitTheMdbRepoUseCaseImplAsync : TheMDBRepoUseCaseAsync{


    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var api: TheMDBRepoApi = retrofit.create(TheMDBRepoApi::class.java)
    override fun getReposForSearchMovieAsync(
        userName: String,
        adultKey: Boolean,
        onSuccess: (List<ResultSearchMovie>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getSearchMovie(TMDB_API_KEY, LANGUAGE, userName, adultKey)
            .enqueue(object : Callback<ResultsParsing> {
                override fun onResponse(
                    call: Call<ResultsParsing>,
                    response: Response<ResultsParsing>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(
                            response.body()?.results
                                ?: throw IllegalStateException("Нулевой результат")
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
        api.getIDMovie(searcheMovie.id, TMDB_API_KEY, LANGUAGE).enqueue(object :
            Callback<Movie> {
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
        api.getGenres(TMDB_API_KEY).enqueue(object : Callback<List<Genres>> {
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
        api.getNowPlaying(TMDB_API_KEY, LANGUAGE).enqueue(object :
            Callback<ResultsParsing> {
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
        api.getPopular(TMDB_API_KEY, LANGUAGE).enqueue(object :
            Callback<ResultsParsing> {
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
        api.getTopRated(TMDB_API_KEY, LANGUAGE).enqueue(object :
            Callback<ResultsParsing> {
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
        api.getUpcoming(TMDB_API_KEY, LANGUAGE).enqueue(object :
            Callback<ResultsParsing> {
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

    override fun getReposForSearchPeopleAsync(
        name: String,
        onSuccess: (List<ResultSearchedPeople>) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getSearshPeople(TMDB_API_KEY, LANGUAGE, name)
            .enqueue(object : Callback<ResultParsingPeople> {
                override fun onResponse(
                    call: Call<ResultParsingPeople>,
                    response: Response<ResultParsingPeople>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(
                            response.body()?.results
                                ?: throw IllegalStateException("Нулевой результат")
                        )
                    } else {
                        OnError(Throwable("Неизвестная ошибка"))
                    }

                }

                override fun onFailure(call: Call<ResultParsingPeople>, t: Throwable) {
                    OnError(t)
                }


            })
    }

    override fun getReposForIdPeopleAsync(
        searchedPeople: ResultSearchedPeople,
        onSuccess: (People) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        api.getIdPeople(searchedPeople.idPeopleSearched, TMDB_API_KEY, LANGUAGE)
            .enqueue(object : Callback<People> {
                override fun onResponse(
                    call: Call<People>,
                    response: Response<People>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()!!)
                    } else {
                        OnError(Throwable("Неизвестная ошибка"))
                    }

                }

                override fun onFailure(call: Call<People>, t: Throwable) {
                    OnError(t)
                }


            })
    }
}