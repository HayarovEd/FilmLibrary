package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.View
import com.edurda77.filmlibrary.domain.NovPlaiyngMovie
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import com.edurda77.filmlibrary.R
import android.widget.TextView

class PlaiyngMovieAdapter(private val list: List<NovPlaiyngMovie>) :
    RecyclerView.Adapter<PlaiyngMovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaiyngMovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlaiyngMovieHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PlaiyngMovieHolder, position: Int) {
        val movie: NovPlaiyngMovie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size
}

