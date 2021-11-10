package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.View
import com.edurda77.filmlibrary.domain.Movie
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.edurda77.filmlibrary.ui.MovieAdapter.OnStateClickListener




class MovieAdapter(private val list: List<Movie>, val onClickListener: OnStateClickListener) :
    RecyclerView.Adapter<MovieHolder>() {
    interface OnStateClickListener {
        fun onStateClick(movie: Movie, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie: Movie = list[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { v: View ->
            onClickListener.onStateClick(movie, position)
        }
    }

    override fun getItemCount(): Int = list.size
}

