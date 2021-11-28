package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.retrofit.NotsMovie

class NoteHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.activity_note, parent, false)) {

    private var titleNote: TextView? = null
    private var contentNote: TextView? = null
    private lateinit var saveButton: Button



    init {
        titleNote = itemView.findViewById(R.id.title_movie_note)
        contentNote = itemView.findViewById(R.id.note_change_movie)
        saveButton = itemView.findViewById(R.id.save_change_nots)

    }
    @SuppressLint("SetTextI18n")
    fun bind(note: NotsMovie) {
        titleNote?.text =  note.titleNote
        contentNote?.text = note.contentNote
        saveButton.setOnClickListener()


    }

}

private fun Button.setOnClickListener() {
    TODO("Not yet implemented")
}
