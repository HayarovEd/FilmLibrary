package com.edurda77.filmlibrary.domain

import androidx.annotation.WorkerThread
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultSearchMovie
import javax.security.auth.callback.Callback

interface TheMDBRepoUseCace {
    @WorkerThread
    fun getReposForUserSync (userName: String): List<ResultSearchMovie>
    fun getReposForUserAsync (userName: String, callback: (List<ResultSearchMovie>)->Unit)
    fun getReposForSearcheMovieSync (searcheMovie: ResultSearchMovie): Movie
    fun getReposForSearcheMovieAsync (searcheMovie: ResultSearchMovie, callback: (Movie)->Unit)
}