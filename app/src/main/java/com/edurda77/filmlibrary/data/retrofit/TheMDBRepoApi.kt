package com.edurda77.filmlibrary.data.retrofit

import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultSearchMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface TheMDBRepoApi {
    @GET("{user}")
    fun getSearchMovie(@Path("user") searchString: String?): Call<List<ResultSearchMovie>>
    fun getIDMovie(@Path("searchedMovie") searchedString: String): Call <Movie>
}