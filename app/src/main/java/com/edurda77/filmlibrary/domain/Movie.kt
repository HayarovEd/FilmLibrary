package com.edurda77.filmlibrary.domain

import java.io.Serializable

class Movie (
    val movieTitle: String,
    val movieGanre: String,
    val movieDuration: String,
    val movieRang: Double,
    val movieYear: Int,
    val movieBudget: Double,
    val movieRevenue: Double,
    val movieSummary: String
) : Serializable

