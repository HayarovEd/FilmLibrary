package com.edurda77.filmlibrary.ui

import android.app.Application
import android.content.Context
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCace

class AppSearsh : Application() {
    val theMDBRepoUseCace: TheMDBRepoUseCace by lazy { todo }
}

val Context.app
    get() = applicationContext as AppSearsh
