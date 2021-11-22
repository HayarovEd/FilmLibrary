package com.edurda77.filmlibrary.domain

import androidx.annotation.WorkerThread
import com.edurda77.filmlibrary.data.Genres
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.ResultSearchMovie

interface TheMDBRepoUseCace {
    @WorkerThread
    fun getReposForSearchMovieSync (userName: String): List<ResultSearchMovie>
    fun getReposForSearchMovieAsync (userName: String, callback: (List<ResultSearchMovie>)->Unit)
    fun getReposForIDMovieSync (searcheMovie: ResultSearchMovie): Movie?
    fun getReposForIDMovieAsync (searcheMovie: ResultSearchMovie, callback: (Movie)->Unit)
    fun getReposForGenresSync():List<Genres>
    fun getReposForGenresAsync(callback: (List<Genres>)->Unit)

}