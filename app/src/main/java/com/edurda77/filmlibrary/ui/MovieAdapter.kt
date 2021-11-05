package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import com.edurda77.filmlibrary.domain.Movie
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

class MovieAdapter(private val list: List<Movie>) :
    RecyclerView.Adapter<MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie: Movie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size
}

