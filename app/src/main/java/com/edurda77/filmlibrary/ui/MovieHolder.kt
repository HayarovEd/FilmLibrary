package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.Movie

class MovieHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.plaiyng_film, parent, false)) {
    private var titleMovie: TextView? = null
    private var idMovie: TextView? = null
    private var popularityMovie: TextView? = null
    private var pictureMovie: ImageView? = null


    init {
        titleMovie = itemView.findViewById(R.id.title_movie)
        idMovie = itemView.findViewById(R.id.id_movie)
        popularityMovie = itemView.findViewById(R.id.popularity_movie)
        pictureMovie = itemView.findViewById(R.id.picture_movie)
    }
    @SuppressLint("SetTextI18n")
    fun bind(movie: Movie) {
        titleMovie?.text = movie.title
        idMovie?.text = idMovie?.text.toString()+movie.id.toString()
        popularityMovie?.text = popularityMovie?.text.toString() + movie.popularity
        //pictureMovie?.setImageResource(23)

    }

}