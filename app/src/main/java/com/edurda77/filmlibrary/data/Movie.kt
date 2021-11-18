package com.edurda77.filmlibrary.data

import java.io.Serializable

class Movie (
    val id : Int,
    val title: String,
    val movieGanre: String,
    val runtime: Int,
    val popularity: Double,
    val release_date: String,
    val budget: Int,
    val revenue: Int,
    val overview: String
) : Serializable

