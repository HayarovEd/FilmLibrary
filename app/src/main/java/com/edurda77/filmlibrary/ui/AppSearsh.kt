package com.edurda77.filmlibrary.ui

import android.app.Application
import android.content.Context
import com.edurda77.filmlibrary.data.RetrofitTheMdbRepoUsecaseImpl
import com.edurda77.filmlibrary.data.RoomNoteRepoImpl
import com.edurda77.filmlibrary.domain.NoteDao
import com.edurda77.filmlibrary.domain.NoteRepo
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace

class AppSearsh : Application() {
    val theMDBRepoUseCace: TheMDBRepoUseCace by lazy { RetrofitTheMdbRepoUsecaseImpl() }
    val noteDao: NoteDao by lazy { RoomNoteRepoImpl(this) }

}

val Context.app
    get() = applicationContext as AppSearsh

