package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.domain.FilmGenre
import com.edurda77.filmlibrary.domain.Movie

class OutHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_out_recycled_view, parent, false)) {
    private val ganreTitle: TextView
    private val movieItem: RecyclerView

    init {
        ganreTitle = itemView.findViewById(R.id.ganre_movie)
        movieItem = itemView.findViewById(R.id.item_movie)
    }

    fun bind(filmGenre: FilmGenre) {
        ganreTitle?.text = filmGenre.genreTitle
        movieItem.recycledViewPool

    }
}