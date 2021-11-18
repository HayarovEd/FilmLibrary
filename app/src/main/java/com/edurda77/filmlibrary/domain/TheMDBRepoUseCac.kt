package com.edurda77.filmlibrary.domain

import androidx.annotation.WorkerThread
import com.edurda77.filmlibrary.data.Movie
import javax.security.auth.callback.Callback

interface TheMDBRepoUseCace {
    @WorkerThread
    fun getReposForUserSync (userName: String): List<Movie>
    fun getReposForUserAsync (userName: String, callback: (List<Movie>)->Unit)
}