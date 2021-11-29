package com.edurda77.filmlibrary.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultSearchMovie(
    val  id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val popularity: Double
) : Serializable
