package com.edurda77.filmlibrary.domain

import androidx.room.*
import com.edurda77.filmlibrary.data.*


@Dao
interface NoteDao {
    @Insert
    fun add(note: NoteMovie)
    @Query("SELECT * FROM $NOTE_TABLE")
    fun getNots(): List<NoteMovie>
    @Query("DELETE FROM $NOTE_TABLE WHERE $NOTE_ID=:id")
    fun delete (id: Int)
    @Query("UPDATE $NOTE_TABLE SET $NOTE_CONTENT=:content," +
            "$NOTE_TITLE=:title   WHERE $NOTE_ID=:id")
    fun update (id: Int, title: String,content: String)
    @Query("DELETE FROM $NOTE_TABLE")
    fun clearNots()
}