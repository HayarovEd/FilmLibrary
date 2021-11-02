package com.edurda77.filmlibrary.ui

import com.edurda77.filmlibrary.ui.PlaiyngMovieAdapter.OnStateClickListener
import android.view.LayoutInflater
import android.view.View
import com.edurda77.filmlibrary.domain.NovPlaiyngMovie
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import com.edurda77.filmlibrary.R
import android.widget.TextView

class PlaiyngMovieAdapter(
    private val onClickListener: OnStateClickListener,
    private val inflater: LayoutInflater,
    private val novPlaiyngMovies: List<NovPlaiyngMovie>
) : RecyclerView.Adapter<PlaiyngMovieAdapter.ViewHolder>() {
    interface OnStateClickListener {
        fun onStateClick(state: NovPlaiyngMovie?, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.plaiyng_film, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val novPlaiyngMovie = novPlaiyngMovies[position]

        //holder.imagePicture.setImageResource(novPlaiyngMovies.get(position).position)
        holder.textTitle.setText(novPlaiyngMovies.get(position).movieTitle)
        holder.textYear.setText(novPlaiyngMovies.get(position).year)
        holder.textRang.setText(novPlaiyngMovies.get(position).rang.toString())
        holder.itemView.setOnClickListener { v: View? ->
            onClickListener.onStateClick(
                novPlaiyngMovie,
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return novPlaiyngMovies.size
    }

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val imagePicture: ImageView
        val textTitle: TextView
        val textYear: TextView
        val textRang: TextView

        init {
            imagePicture = view.findViewById(R.id.picture_movie)
            textTitle = view.findViewById(R.id.title_movie)
            textYear = view.findViewById(R.id.year_movie)
            textRang = view.findViewById(R.id.rang_movie)
        }
    }
}