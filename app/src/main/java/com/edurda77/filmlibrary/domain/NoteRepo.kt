package com.edurda77.filmlibrary.domain

import com.edurda77.filmlibrary.data.NoteMovie

interface NoteRepo {
    fun add(note: NoteMovie)
    fun getNots(): List<NoteMovie>
    fun delete (id: Int)
    fun update (id: Int, note: NoteMovie)
    fun clearNots(): List<NoteMovie>
}