package com.edurda77.filmlibrary.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val movieGanre: String,
    val runtime: Int,
    val popularity: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    val budget: Int,
    val revenue: Int,

    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String
) : Serializable

