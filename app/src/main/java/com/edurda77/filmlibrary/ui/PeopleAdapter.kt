package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.edurda77.filmlibrary.data.ResultSearchedPeople

class PeopleAdapter (private val list: List<ResultSearchedPeople>,
                     private val onClickListener: OnStateClickListener) :
    RecyclerView.Adapter<PeopleHolder>()
{
    interface OnStateClickListener {
        fun onStateClick(people: ResultSearchedPeople, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PeopleHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PeopleHolder, position: Int) {


        val people: ResultSearchedPeople = list[position]
        holder.bind(people)

        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(people, position)
        }
    }

    override fun getItemCount(): Int = list.size
}