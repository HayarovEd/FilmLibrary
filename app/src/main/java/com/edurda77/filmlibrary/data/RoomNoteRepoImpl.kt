package com.edurda77.filmlibrary.data

import android.content.Context
import androidx.room.Room
import com.edurda77.filmlibrary.domain.NoteDao
import com.edurda77.filmlibrary.domain.NoteRepo
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

    override fun getNots(): List<NoteMovie> {
        return noteDao.getNots()
    }

    override fun delete(id: Int) {

        noteDao.delete(id)
        TODO("Not yet implemented")
    }



    override fun update(id: Int, title: String,content: String) {
        noteDao.update(id,title, content)
    }

    override fun clearNots(){
        noteDao.clearNots()

    }
}