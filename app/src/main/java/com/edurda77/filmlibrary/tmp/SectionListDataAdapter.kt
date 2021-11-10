package com.edurda77.filmlibrary.tmp

import android.content.Context

import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.tmp.SectionListDataAdapter.SingleItemRowHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.edurda77.filmlibrary.R
import android.widget.TextView
import com.edurda77.filmlibrary.domain.Movie
import java.util.ArrayList

class SectionListDataAdapter(
    private val mContext: Context,
    private val itemsList: ArrayList<Movie>?,
    private val onClickListener: OnStateClickListener
) : RecyclerView.Adapter<SingleItemRowHolder>() {
    interface OnStateClickListener {
        fun onStateClick(movie: Movie?, position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.plaiyng_film, null)
        return SingleItemRowHolder(v)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {
        val movie = itemsList!![i]
        holder.titleMovie.text = movie.movieTitle
        holder.yearMovie.setText(movie.year)
        holder.rangMovie.setText(movie.rang.toInt())
        holder.itemView.setOnClickListener { v: View? -> onClickListener.onStateClick(movie, i) }

        /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    override fun getItemCount(): Int {
        return itemsList?.size ?: 0
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleMovie: TextView
        var yearMovie: TextView
        var rangMovie: TextView

        //protected ImageView itemImage;
        init {
            titleMovie = view.findViewById(R.id.title_movie)
            yearMovie = view.findViewById(R.id.year_movie)
            rangMovie = view.findViewById(R.id.rang_movie)
            //this.itemImage = (ImageView) view.findViewById(R.id.itemImage);
        }

    }


}