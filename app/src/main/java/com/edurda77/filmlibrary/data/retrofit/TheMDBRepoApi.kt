package com.edurda77.filmlibrary.data.retrofit

import com.edurda77.filmlibrary.data.Genres
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultsParsing
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMDBRepoApi {
    @GET("/search/movie")
    fun getSearchMovie(

        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") searchString: String?
       /* @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean*/

    ): Call<ResultsParsing>
//"https://api.themoviedb.org/3/search/movie?api_key=2513408bca2d22ed908b2b3badf57939&language=ru-RU&query=%D0%BC%D0%B0%D1%82%D1%80%D0%B8%D1%86%D0%B0&"

    fun getIDMovie(@Path("searchedMovie") searchedString: String): Call<Movie>
    @GET ("genre/movie/list")
    fun getGenres(
        @Query ("api_key") apiKey: String
    ): Call<List<Genres>>
}
