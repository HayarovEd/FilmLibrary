package com.edurda77.filmlibrary.data

import com.edurda77.filmlibrary.data.ResultSearсhMovies

data class ResultsParsing(
    val  page: Int,
    val results: Array<ResultSearсhMovies>
)
