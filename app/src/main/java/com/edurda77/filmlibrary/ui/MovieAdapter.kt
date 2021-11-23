package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultSearchMovie


class MovieAdapter(private val list: List<ResultSearchMovie>, val onClickListener: OnStateClickListener) :
    RecyclerView.Adapter<MovieSearchHolder>() {
    interface OnStateClickListener {
        fun onStateClick(movie: ResultSearchMovie, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieSearchHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieSearchHolder, position: Int) {
        val movie: ResultSearchMovie = list[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(movie, position)
        }
    }

    override fun getItemCount(): Int = list.size
}

