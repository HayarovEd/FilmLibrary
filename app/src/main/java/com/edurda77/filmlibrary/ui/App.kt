package com.edurda77.filmlibrary.ui

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.edurda77.filmlibrary.data.AlarmUpcomingMovieCaseImpl
import com.edurda77.filmlibrary.data.LocationRepoImpl
import com.edurda77.filmlibrary.data.RetrofitTheMdbRepoUseCaseImpl
import com.edurda77.filmlibrary.data.RoomNoteRepoImpl
import com.edurda77.filmlibrary.domain.AlarmUpcomingMovieCase
import com.edurda77.filmlibrary.domain.LocationRepo
import com.edurda77.filmlibrary.domain.NoteDao

import com.edurda77.filmlibrary.domain.TheMDBRepoUseCaseSync
private const val DEFAULT_SHARED_KEY = "DEFAULT_SHARED_KEY"
class App : Application() {
    val theMDBRepoUseCaseSync: TheMDBRepoUseCaseSync by lazy { RetrofitTheMdbRepoUseCaseImpl() }
    val noteDao: NoteDao by lazy { RoomNoteRepoImpl(this) }
    val sharedPreferences: SharedPreferences
            by lazy {getSharedPreferences(DEFAULT_SHARED_KEY, MODE_PRIVATE)}
    //val noteRepoSync: NoteRepo by lazy { NoteRepoImpl() }
    val locationRepo: LocationRepo by lazy { LocationRepoImpl() }
    val alarmUpcomingMovieCase: AlarmUpcomingMovieCase by lazy {AlarmUpcomingMovieCaseImpl()}

}

val Context.app
    get() = applicationContext as App

