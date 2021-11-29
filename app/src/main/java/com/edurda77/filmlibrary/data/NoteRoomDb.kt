package com.edurda77.filmlibrary.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edurda77.filmlibrary.domain.NoteDao

@Database(
    entities = [NoteMovie:: class],
    version = 1
)
abstract class NoteRoomDb: RoomDatabase() {
    abstract fun noteDao():NoteDao
}