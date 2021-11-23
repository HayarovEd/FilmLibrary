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
import java.lang.StringBuilder

class MovieSearchHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.plaiyng_film, parent, false)) {

    private var titleMovie: TextView? = null
    private var idMovie: TextView? = null
    private var genreMovie: TextView? = null
    private var pictureMovie: ImageView
    private var beginURL = "https://image.tmdb.org/t/p/w500/"


    init {
        titleMovie = itemView.findViewById(R.id.title_movie)
        idMovie = itemView.findViewById(R.id.id_movie)
        genreMovie = itemView.findViewById(R.id.genre_movie)
        pictureMovie = itemView.findViewById(R.id.picture_movie_view)
    }
    @SuppressLint("SetTextI18n")
    fun bind(movie: ResultSearchMovie) {
        titleMovie?.text =   movie.title
        idMovie?.text = idMovie?.text.toString()+movie.id.toString()
        val sb: StringBuilder? =null
        movie.genreIds.forEach {
            if (sb != null) {
                sb.append(it).append(", ")
            }

        }
        genreMovie?.text = genreMovie?.text.toString()+sb.toString()
        Glide.with(this.itemView.context).load(beginURL+movie.posterPath).into(pictureMovie)
        //pictureMovie?.setImageResource(23)

    }

}