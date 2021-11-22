package com.edurda77.filmlibrary.domain

import androidx.annotation.WorkerThread
import com.edurda77.filmlibrary.data.Genres
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultSearchMovie

interface TheMDBRepoUseCace {
    @WorkerThread
    @Throws(Throwable::class)
    fun getReposForSearchMovieSync (userName: String): List<ResultSearchMovie>?
    fun getReposForIDMovieSync (searcheMovie: ResultSearchMovie): Movie?
    fun getReposForGenresSync():List<Genres>?


    fun getReposForSearchMovieAsync (userName: String, onSuccess: (List<ResultSearchMovie>)->Unit,
                                     OnError: (Throwable) ->Unit)
    fun getReposForIDMovieAsync (searcheMovie: ResultSearchMovie,
                                 onSuccess: (Movie)->Unit,
                                 OnError: (Throwable) ->Unit)
    fun getReposForGenresAsync(onSuccess: (List<Genres>)->Unit,
                               OnError: (Throwable) ->Unit)

}