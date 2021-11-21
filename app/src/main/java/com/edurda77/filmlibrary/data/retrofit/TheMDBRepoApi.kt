package com.edurda77.filmlibrary.data.retrofit

import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultSearchMovie
import com.edurda77.filmlibrary.data.ResultsParsing
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMDBRepoApi {
    @GET("3/search/movie/{user}")
    fun getSearchMovie(
        @Path("user") searchString: String?,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,

        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean

    ): Call<List<ResultSearchMovie>>

    fun getIDMovie(@Path("searchedMovie") searchedString: String): Call<Movie>
}
