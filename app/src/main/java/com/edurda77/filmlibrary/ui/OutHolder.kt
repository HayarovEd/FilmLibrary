package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.FilmGenre


class OutHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val onClickListener: MovieAdapter.OnStateClickListener
) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_out_recycled_view, parent, false)) {
    private val genreTitle: TextView = itemView.findViewById(R.id.ganre_movie)
    val movieItem: RecyclerView = itemView.findViewById(R.id.item_movie)

    fun bindGenre(filmGenre: FilmGenre) {
        genreTitle.text = filmGenre.genreTitle
        val layoutManager = LinearLayoutManager(
            movieItem.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        layoutManager.initialPrefetchItemCount = filmGenre.movieList.size


        val subItemAdapter = MovieAdapter(filmGenre.movieList,onClickListener)
        movieItem.layoutManager = layoutManager
        movieItem.adapter = subItemAdapter



    }
}