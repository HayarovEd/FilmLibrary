package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.domain.NovPlaiyngMovie

class PlaiyngMovieHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.plaiyng_film, parent, false)) {
    private var titleMovie: TextView? = null
    private var yearMovie: TextView? = null
    private var rangMovie: TextView? = null
    private var pictureMovie: ImageView? = null
    init {
        titleMovie = itemView.findViewById(R.id.title_movie)
        yearMovie = itemView.findViewById(R.id.year_movie)
        rangMovie = itemView.findViewById(R.id.rang_movie)
        pictureMovie = itemView.findViewById(R.id.picture_movie)
    }
    fun bind(movie: NovPlaiyngMovie) {
        titleMovie?.text = movie.movieTitle
        yearMovie?.text = movie.year.toString()
        rangMovie?.text = movie.rang.toString()
        //pictureMovie?.setImageResource(23)

    }

}