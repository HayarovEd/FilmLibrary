package com.edurda77.filmlibrary.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.edurda77.filmlibrary.data.FilmGenre


class OutAdapter (private val itemList: List<FilmGenre>,
                  private val onClickListener: MovieAdapter.OnStateClickListener) :
    RecyclerView.Adapter<OutHolder>() {
    private val viewPool = RecycledViewPool()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OutHolder(inflater, parent, onClickListener)
    }

    override fun onBindViewHolder(ganreViewHolder: OutHolder, position: Int) {
        val itemHolder = itemList[position]

        ganreViewHolder.bindGenre(itemHolder)

        ganreViewHolder.movieItem.setRecycledViewPool(viewPool)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}