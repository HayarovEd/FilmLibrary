package com.edurda77.filmlibrary.data

import com.edurda77.filmlibrary.BuildConfig.TMDB_API_KEY
import com.edurda77.filmlibrary.data.retrofit.TheMDBRepoApi
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCaseSync
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"
const val LANGUAGE = "ru-RU"


class RetrofitTheMdbRepoUseCaseImpl : TheMDBRepoUseCaseSync {


    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var api: TheMDBRepoApi = retrofit.create(TheMDBRepoApi::class.java)


    override fun getReposForSearchMovieSync(
        userName: String,
        adultKey: Boolean,

    ): List<ResultSearchMovie> {

        val resultsParsing: ResultsParsing? =
            api.getSearchMovie(TMDB_API_KEY, LANGUAGE, userName,  adultKey)
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

    override fun getReposForNowPlayingMovieSync(): List<ResultSearchMovie> {
        val resultsParsing: ResultsParsing? = api.getNowPlaying(TMDB_API_KEY, LANGUAGE)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForPopularMovieSync(): List<ResultSearchMovie> {
        val resultsParsing: ResultsParsing? = api.getPopular(TMDB_API_KEY, LANGUAGE)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForTopRatedMovieSync(): List<ResultSearchMovie> {
        val resultsParsing: ResultsParsing? = api.getTopRated(TMDB_API_KEY, LANGUAGE)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForUpcomingMovieSync(): List<ResultSearchMovie> {
        val resultsParsing: ResultsParsing? = api.getUpcoming(TMDB_API_KEY, LANGUAGE)
            .execute().body()

        return parsingForSync(resultsParsing)
    }

    override fun getReposForSearchPeopleSync(name: String): List<ResultSearchedPeople> {
        val resultsParsingPeople: ResultParsingPeople? =
            api.getSearshPeople(TMDB_API_KEY, LANGUAGE, name)
                .execute().body()
        return parsingForPeopleSync(resultsParsingPeople)
    }

    override fun getReposForIdPeopleSync(searchedPeople: ResultSearchedPeople): People? {
        return api.getIdPeople(searchedPeople.idPeopleSearched, TMDB_API_KEY, LANGUAGE)
            .execute().body()
    }



    private fun parsingForSync(resultsParsing: ResultsParsing?): List<ResultSearchMovie> {
        val resultSearch = emptyList<ResultSearchMovie>().toMutableList()

        resultsParsing?.results?.forEach {
            resultSearch.add(it)
        }
        return resultSearch
    }

    private fun parsingForPeopleSync(resultsParsing: ResultParsingPeople?): List<ResultSearchedPeople> {
        val resultSearch = emptyList<ResultSearchedPeople>().toMutableList()

        resultsParsing?.results?.forEach {
            resultSearch.add(it)
        }
        return resultSearch
    }


}