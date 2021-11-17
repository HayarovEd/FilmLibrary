package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.edurda77.filmlibrary.data.ResultSearсhMovies


class MovieSearchAdapter(private val list: List<ResultSearсhMovies>, val onClickListener: OnStateClickListener) :
    RecyclerView.Adapter<MovieSearchHolder>() {
    interface OnStateClickListener {
        fun onStateClick(movie: ResultSearсhMovies, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieSearchHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieSearchHolder, position: Int) {
        val movie: ResultSearсhMovies = list[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { v: View ->
            onClickListener.onStateClick(movie, position)
        }
    }

    override fun getItemCount(): Int = list.size
}

