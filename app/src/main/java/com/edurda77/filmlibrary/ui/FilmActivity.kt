package com.edurda77.filmlibrary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.domain.Movie

class FilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val title : TextView? = findViewById (R.id.title_movie)
        val rang : TextView? = findViewById (R.id.rang_movie)
        val ganre : TextView? = findViewById (R.id.ganre_movie)
        val year : TextView? = findViewById (R.id.year_movie)
        val duration : TextView? = findViewById (R.id.duration_movie)
        val budget : TextView? = findViewById (R.id.budget_movie)
        val revenue : TextView? = findViewById (R.id.revenue_movie)
        val summary : TextView? = findViewById (R.id.summary_movie)
        super.onCreate(savedInstanceState)
        val arguments = intent.extras

        val movie: Movie?
        if (arguments != null) {
            movie = arguments.getSerializable(Movie::class.java.getSimpleName()) as Movie?
            if (movie != null) {
                title?.setText(movie.movieTitle)
                rang?.setText(movie.movieRang.toString())
                ganre?.setText(movie.movieGanre)
                year?.setText(movie.movieYear)
                duration?.setText(movie.movieDuration)
                budget?.setText(movie.movieBudget.toString())
                revenue?.setText(movie.movieRevenue.toString())
                summary?.setText(movie.movieSummary)
            }


        }
        setContentView(R.layout.activity_film)

    }
}