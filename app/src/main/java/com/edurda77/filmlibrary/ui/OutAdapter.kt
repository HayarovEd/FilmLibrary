package com.edurda77.filmlibrary.ui


import android.content.Context
import com.edurda77.filmlibrary.domain.FilmGenre
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import android.view.ViewGroup
import android.view.LayoutInflater


class OutAdapter(
    private val itemList: List<FilmGenre>,
    private val mContext: Context,
    private val onClickListener: MovieAdapter.OnStateClickListener

) :
    RecyclerView.Adapter<OutHolder>() {
    private val viewPool = RecycledViewPool()
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OutHolder(inflater, parent)
    }

    override fun onBindViewHolder(ganreViewHolder: OutHolder, position: Int) {

        val itemHolder = itemList[position]

        ganreViewHolder.bindGanre(itemHolder)

        ganreViewHolder.movieItem.setRecycledViewPool(viewPool)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}