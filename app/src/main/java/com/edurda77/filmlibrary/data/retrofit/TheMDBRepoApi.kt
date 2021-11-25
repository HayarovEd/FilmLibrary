package com.edurda77.filmlibrary.data.retrofit

import com.edurda77.filmlibrary.data.Genres
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultsParsing
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMDBRepoApi {
    @GET("search/movie")
    fun getSearchMovie(

        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") searchString: String?,
        @Query("include_adult") includeAdult: Boolean

         //@Query("include_adult") includeAdult: Boolean*/

    ): Call<ResultsParsing>


    @GET("movie/{movie_id}")
    fun getIDMovie(
        @Path("movie_id") searchedString: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movie>

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String
    ): Call<List<Genres>>
    @GET ("movie/now_playing")
    fun getNowPlaying (
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Call<ResultsParsing>
    @GET ("movie/popular")
    fun getPopular (
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ResultsParsing>
    @GET ("movie/top_rated")
    fun getTopRated (
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ResultsParsing>
    @GET ("movie/upcoming")
    fun getUpcoming (
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ResultsParsing>

}
