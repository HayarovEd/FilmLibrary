package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.ResultSearchMovie

class MovieHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.plaiyng_film, parent, false)) {
    private var titleMovie: TextView? = null
    private var idMovie: TextView? = null
    private var popularityMovie: TextView? = null
    private var pictureMovie: ImageView
    private var beginURL = "https://image.tmdb.org/t/p/w500/"


    init {
        titleMovie = itemView.findViewById(R.id.title_movie)
        idMovie = itemView.findViewById(R.id.id_movie)
        popularityMovie = itemView.findViewById(R.id.popularity_movie)
        pictureMovie = itemView.findViewById(R.id.picture_movie_view)
    }
    @SuppressLint("SetTextI18n")
    fun bind(movie: ResultSearchMovie) {
        titleMovie?.text = movie.title
        idMovie?.text = idMovie?.text.toString()+movie.id.toString()
        popularityMovie?.text = popularityMovie?.text.toString() + movie.popularity
        pictureMovie = itemView.findViewById(R.id.picture_movie_view)
        Glide.with(this.itemView.context)
            .load(beginURL+movie.posterPath)
            .placeholder(R.drawable.video)
            .into(pictureMovie)

    }

}