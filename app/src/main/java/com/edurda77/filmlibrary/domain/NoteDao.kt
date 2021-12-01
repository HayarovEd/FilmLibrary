package com.edurda77.filmlibrary.domain

import androidx.room.*
import com.edurda77.filmlibrary.data.*


@Dao
interface NoteDao {
    @Insert
    fun add(note: NoteMovie)
    @Query("SELECT * FROM $NOTE_TABLE")
    fun getNotes(): List<NoteMovie>
    @Query("DELETE FROM $NOTE_TABLE WHERE $NOTE_ID=:id")
    fun delete (id: Int)
    @Query("UPDATE $NOTE_TABLE SET $NOTE_CONTENT=:content  WHERE $NOTE_ID=:id")
    fun update (id: Int, content: String)
    @Query("DELETE FROM $NOTE_TABLE")
    fun clearNotes()
}