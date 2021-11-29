package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.NoteMovie

class NoteHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_note, parent, false)) {

    private var titleNote: TextView? = null
    private var contentNote: TextView? = null




    init {
        titleNote = itemView.findViewById(R.id.title_note)
        contentNote = itemView.findViewById(R.id.content_note)


    }
    @SuppressLint("SetTextI18n")
    fun bind(note: NoteMovie) {
        titleNote?.text =  note.titleNote
        contentNote?.text = note.contentNote



    }

}

private fun Button.setOnClickListener() {
    TODO("Not yet implemented")
}
