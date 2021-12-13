package com.edurda77.filmlibrary.data

import com.google.gson.annotations.SerializedName

data class ResultsParsing(
    val  page: Int,
    val results: List<ResultSearchMovie>,
    @SerializedName("total_pages")
    val pageTotal: Int
)
