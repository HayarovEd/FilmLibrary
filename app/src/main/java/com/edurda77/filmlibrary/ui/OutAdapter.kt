package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.domain.FilmGenre

class OutAdapter(private val list: List<FilmGenre>) :
    RecyclerView.Adapter<OutHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OutHolder(inflater, parent)
    }


    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: OutHolder, position: Int) {
        val filmGenre: FilmGenre = list[position]
        holder.bind(filmGenre)
    }
}