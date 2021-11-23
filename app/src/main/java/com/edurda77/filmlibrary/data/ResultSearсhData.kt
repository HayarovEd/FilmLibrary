package com.edurda77.filmlibrary.data

import com.google.gson.annotations.SerializedName

data class ResultSearchMovie(
    val  id: Int,
    val title: String,
    val overview: String,
    @SerializedName("logo_path")
    val logoPath : String
)
