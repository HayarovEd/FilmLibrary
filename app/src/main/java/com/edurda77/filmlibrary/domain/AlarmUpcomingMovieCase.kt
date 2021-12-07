package com.edurda77.filmlibrary.domain

import android.content.Context
import com.edurda77.filmlibrary.data.ResultSearchMovie

interface AlarmUpcomingMovieCase {
    fun setAlarm (upcomingMovie: ResultSearchMovie, context:Context)
}