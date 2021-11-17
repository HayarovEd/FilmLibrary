package com.edurda77.filmlibrary.data

import java.io.Serializable

class Movie (
    val movieId : Int,
    val movieTitle: String,
    val movieGanre: String,
    val movieDuration: String,
    val movieRang: Double,
    val movieYear: Int,
    val movieBudget: Double,
    val movieRevenue: Double,
    val movieSummary: String
) : Serializable

