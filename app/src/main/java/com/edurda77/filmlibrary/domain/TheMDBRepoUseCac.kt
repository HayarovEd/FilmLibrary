package com.edurda77.filmlibrary.domain

import androidx.annotation.WorkerThread
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultSearchMovie
import javax.security.auth.callback.Callback

interface TheMDBRepoUseCace {
    @WorkerThread
    fun getReposForSearchMovieSync (userName: String): List<ResultSearchMovie>
    fun getReposForSearchMovieAsync (userName: String, callback: (List<ResultSearchMovie>)->Unit)
    fun getReposForIDMovieSync (searcheMovie: ResultSearchMovie): Movie?
    fun getReposForIDMovieAsync (searcheMovie: ResultSearchMovie, callback: (Movie)->Unit)
}