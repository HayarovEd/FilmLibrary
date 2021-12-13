package com.edurda77.filmlibrary.domain

import androidx.annotation.WorkerThread
import com.edurda77.filmlibrary.data.*

interface TheMDBRepoUseCaseSync {
    @WorkerThread
    @Throws(Throwable::class)
    fun getReposForSearchMovieSync (userName: String, adultKey:Boolean): List<ResultSearchMovie>?
    fun getReposForIDMovieSync (searcheMovie: ResultSearchMovie): Movie?
    fun getReposForGenresSync():List<Genres>?
    fun getReposForNowPlayingMovieSync (): List<ResultSearchMovie>?
    fun getReposForPopularMovieSync (): List<ResultSearchMovie>?
    fun getReposForTopRatedMovieSync (): List<ResultSearchMovie>?
    fun getReposForUpcomingMovieSync (): List<ResultSearchMovie>?
    fun getReposForSearchPeopleSync (name:String): List<ResultSearchedPeople>?
    fun getReposForIdPeopleSync (searchedPeople:ResultSearchedPeople): People?




}