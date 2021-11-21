package com.edurda77.filmlibrary.ui

import android.app.Application
import android.content.Context
import com.edurda77.filmlibrary.data.WebTheMdbRepoUsecaseImpl
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace

class AppSearsh : Application() {
    val theMDBRepoUseCace: TheMDBRepoUseCace by lazy { WebTheMdbRepoUsecaseImpl() }
    val theMDBRepoSearchMovieCac: TheMDBRepoUseCace by lazy { WebTheMdbRepoUsecaseImpl()}
}

val Context.app
    get() = applicationContext as AppSearsh

