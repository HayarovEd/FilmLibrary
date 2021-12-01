package com.edurda77.filmlibrary.ui

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.edurda77.filmlibrary.data.RetrofitTheMdbRepoUseCaseImpl
import com.edurda77.filmlibrary.data.RoomNoteRepoImpl
import com.edurda77.filmlibrary.domain.NoteDao

import com.edurda77.filmlibrary.domain.TheMDBRepoUseCaseSync
private const val DEFAUL_SHARED_KEY = "DEFAUL_SHARED_KEY"
class App : Application() {
    val theMDBRepoUseCaceSync: TheMDBRepoUseCaseSync by lazy { RetrofitTheMdbRepoUseCaseImpl() }
    val noteDao: NoteDao by lazy { RoomNoteRepoImpl(this) }
    val sharedPrefernces: SharedPreferences
            by lazy {getSharedPreferences(DEFAUL_SHARED_KEY, MODE_PRIVATE)}
    //val noteRepoSync: NoteRepo by lazy { NoteRepoImpl() }

}

val Context.app
    get() = applicationContext as App

