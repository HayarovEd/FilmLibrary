package com.edurda77.filmlibrary.data

import android.content.Context
import androidx.room.Room
import com.edurda77.filmlibrary.domain.NoteDao

private const val DB_PATH="notes.db"
class RoomNoteRepoImpl (context:Context): NoteDao{
    private val noteDao:NoteDao
    = Room.databaseBuilder(
            context,
            NoteRoomDb::class.java,
            DB_PATH
        ).build().noteDao()


    override fun add(note: NoteMovie) {
        noteDao.add(note)
    }

    override fun getNotes(): List<NoteMovie> {
        return noteDao.getNotes()
    }

    override fun delete(id: Int) {

        noteDao.delete(id)

    }



    override fun update(id: Int, content: String) {
        noteDao.update(id,content)
    }

    override fun clearNotes(){
        noteDao.clearNotes()

    }
}