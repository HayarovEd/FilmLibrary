package com.edurda77.filmlibrary.domain

import com.edurda77.filmlibrary.data.*

interface TheMDBRepoUseCaseAsync {
    fun getReposForSearchMovieAsync (userName: String, adultKey:Boolean, onSuccess: (List<ResultSearchMovie>)->Unit,
                                     OnError: (Throwable) ->Unit)
    fun getReposForIDMovieAsync (searcheMovie: ResultSearchMovie,
                                 onSuccess: (Movie)->Unit,
                                 OnError: (Throwable) ->Unit)
    fun getReposForGenresAsync(onSuccess: (List<Genres>)->Unit,
                               OnError: (Throwable) ->Unit)
    fun getReposForNowPlayingMovieAsync (onSuccess: (List<ResultSearchMovie>)->Unit,
                                         OnError: (Throwable) ->Unit)
    fun getReposForPopularMovieAsync (onSuccess: (List<ResultSearchMovie>)->Unit,
                                      OnError: (Throwable) ->Unit)
    fun getReposForTopRatedMovieAsync (onSuccess: (List<ResultSearchMovie>)->Unit,
                                       OnError: (Throwable) ->Unit)
    fun getReposForUpcomingMovieAsync (onSuccess: (List<ResultSearchMovie>)->Unit,
                                       OnError: (Throwable) ->Unit)
    fun getReposForSearchPeopleAsync (name: String, onSuccess: (List<ResultSearchedPeople>)->Unit,
                                      OnError: (Throwable) ->Unit)
    fun getReposForIdPeopleAsync (searchedPeople: ResultSearchedPeople,
                                  onSuccess: (People)->Unit,
                                  OnError: (Throwable) ->Unit)
}