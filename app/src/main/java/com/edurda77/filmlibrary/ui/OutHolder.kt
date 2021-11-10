package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.domain.FilmGenre


class OutHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_out_recycled_view, parent, false)) {
    val ganreTitle: TextView
    val movieItem: RecyclerView
    init {
        ganreTitle = itemView.findViewById(R.id.ganre_movie)
        movieItem = itemView.findViewById(R.id.item_movie)
    }
    fun bindGanre(filmGanre: FilmGenre) {
        ganreTitle.text = filmGanre.genreTitle
        val layoutManager = LinearLayoutManager(
            movieItem.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        layoutManager.initialPrefetchItemCount = filmGanre.movieList.size


        val subItemAdapter = MovieAdapter(filmGanre.movieList)
        movieItem.layoutManager = layoutManager
        movieItem.adapter = subItemAdapter



    }
}