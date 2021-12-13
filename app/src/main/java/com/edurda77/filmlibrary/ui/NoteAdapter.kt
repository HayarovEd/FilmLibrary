package com.edurda77.filmlibrary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.data.NoteMovie

class NoteAdapter (private val list: List<NoteMovie>,
                   private val onClickListener: OnStateClickListener) :
    RecyclerView.Adapter<NoteHolder>()
{
    interface OnStateClickListener {
        fun onStateClick(note: NoteMovie, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {


        val note: NoteMovie = list[position]
        holder.bind(note)

        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(note, position)
        }
    }

    override fun getItemCount(): Int = list.size
}