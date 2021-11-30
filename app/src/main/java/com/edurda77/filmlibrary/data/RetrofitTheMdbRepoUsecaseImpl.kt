package com.edurda77.filmlibrary.data

import com.edurda77.filmlibrary.BuildConfig.TMDB_API_KEY
import com.edurda77.filmlibrary.data.retrofit.TheMDBRepoApi
import com.edurda77.filmlibrary.domain.NoteRepo
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.themoviedb.org/3/"
const val LANGUAGE = "ru-RU"



class RetrofitTheMdbRepoUsecaseImpl : TheMDBRepoUseCace, NoteRepo {


    private val cacheNots : MutableList<NoteMovie> = mutableListOf()
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var api: TheMDBRepoApi = retrofit.create(TheMDBRepoApi::class.java)


    override fun getReposForSearchMovieSync(userName: String, adultKey:Boolean): List<ResultSearchMovie> {

        val resultsParsing: ResultsParsing? = api.getSearchMovie(TMDB_API_KEY, LANGUAGE, userName,adultKey)
            .execute().body()
        return parsingForSync(resultsParsing)

    }


    override fun getReposForIDMovieSync(searcheMovie: ResultSearchMovie): Movie? {

        return api.getIDMovie(searcheMovie.id, TMDB_API_KEY, LANGUAGE)
            .execute().body()
    }


    override fun getReposForGenresSync(): List<Genres> {

        return api.getGenres(TMDB_API_KEY).execute().body() ?: emptyList()

    }

    override fun getReposForNowPlayingMovieSync(): List<ResultSearchMovie>? {
        val resultsParsing: ResultsParsing? = api.getNowPlaying(TMDB_API_KEY, LANGUAGE)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForPopularMovieSync(): List<ResultSearchMovie>? {
        val resultsParsing: ResultsParsing? = api.getPopular(TMDB_API_KEY, LANGUAGE)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForTopRatedMovieSync(): List<ResultSearchMovie>? {
        val resultsParsing: ResultsParsing? = api.getTopRated(TMDB_API_KEY, LANGUAGE)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForUpcomingMovieSync(): List<ResultSearchMovie>? {
        val resultsParsing: ResultsParsing? = api.getUpcoming(TMDB_API_KEY, LANGUAGE)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForSearchPeopleSync(name: String): List<ResultSearchedPeople>? {
        TODO("Not yet implemented")
    }

    override fun getReposForIdPeopleSync(searchedPeople: ResultSearchedPeople): People? {
        TODO("Not yet implemented")
    }

    override fun getReposForSearchMovieAsync(
        userName: String,
        adultKey:Boolean,
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
        api.getIDMovie(searcheMovie.id, TMDB_API_KEY, LANGUAGE).enqueue(object : Callback<Movie> {
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
        api.getNowPlaying(TMDB_API_KEY, LANGUAGE).enqueue(object : Callback<ResultsParsing> {
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
        api.getPopular(TMDB_API_KEY, LANGUAGE).enqueue(object : Callback<ResultsParsing> {
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
        api.getTopRated(TMDB_API_KEY, LANGUAGE).enqueue(object : Callback<ResultsParsing> {
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
        api.getUpcoming(TMDB_API_KEY, LANGUAGE).enqueue(object : Callback<ResultsParsing> {
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
        TODO("Not yet implemented")
    }

    override fun getReposForIdPeopleAsync(
        searchedPeople: ResultSearchedPeople,
        onSuccess: (People) -> Unit,
        OnError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    fun parsingForSync(resultsParsing: ResultsParsing?): List<ResultSearchMovie> {
        val resultSearch = emptyList<ResultSearchMovie>().toMutableList()

        resultsParsing?.results?.forEach {
            resultSearch.add(it)
        }
        return resultSearch
    }

    override fun add(note: NoteMovie) {
        cacheNots.add(note)
    }

    override fun getNots(): List<NoteMovie> {
            return ArrayList<NoteMovie>(cacheNots)
    }

    override fun delete(id: Int) {
        val indexOfDelete = cacheNots.indexOfFirst {
            it.idNote==id
        }
        cacheNots.removeAt(indexOfDelete)

    }

    override fun update(id: Int, note: NoteMovie) {
        val indexOfDelete = cacheNots.indexOfFirst {
            it.idNote==id
        }
        cacheNots.removeAt(indexOfDelete)
        cacheNots.add(indexOfDelete,note)
    }

    override fun clearNots() {
        cacheNots.clear()


    }
}