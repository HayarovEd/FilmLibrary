package com.edurda77.filmlibrary.ui


import com.edurda77.filmlibrary.domain.FilmGenre
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.ui.SecondAdapter.ItemViewHolder
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.edurda77.filmlibrary.R
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.TextView

class SecondAdapter internal constructor(private val itemList: List<FilmGenre>) :
    RecyclerView.Adapter<ItemViewHolder>() {
    private val viewPool = RecycledViewPool()
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_out_recycled_view, viewGroup, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder, i: Int) {
        val item = itemList[i]
        itemViewHolder.ganreTitle.text = item.genreTitle


        val layoutManager = LinearLayoutManager(
            itemViewHolder.movieItem.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        layoutManager.initialPrefetchItemCount = item.movieList.size


        val subItemAdapter = MovieAdapter(item.movieList)
        itemViewHolder.movieItem.layoutManager = layoutManager
        itemViewHolder.movieItem.adapter = subItemAdapter
        itemViewHolder.movieItem.setRecycledViewPool(viewPool)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ganreTitle: TextView
        val movieItem: RecyclerView

        init {
            ganreTitle = itemView.findViewById(R.id.ganre_movie)
            movieItem = itemView.findViewById(R.id.item_movie)
        }
    }
}