package com.edurda77.filmlibrary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.domain.Movie

class FilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        var titlEditText: EditText = findViewById(R.id.title_movie)
        var ganreEditText: EditText = findViewById(R.id.ganre_movie)
        var durationEditText: EditText = findViewById(R.id.duration_movie)
        var rangEditText: EditText = findViewById(R.id.rang_movie)
        var yearEditText: EditText = findViewById(R.id.year_movie)
        var budgetEditText: EditText = findViewById(R.id.budget_movie)
        var revenueEditText: EditText = findViewById(R.id.revenue_movie)
        var summaryEditText: EditText = findViewById(R.id.summary_movie)
        val arguments = intent.extras

        val movie: Movie
        if (arguments != null) {
            movie = arguments.getSerializable(Movie::class.java.getSimpleName()) as Movie
            titlEditText.setText(movie.movieTitle)
            rangEditText.setText(movie.movieRang.toString())
            ganreEditText.setText(movie.movieGanre)
            yearEditText.setText(movie.movieYear.toString())
            durationEditText.setText(movie.movieDuration)
            budgetEditText.setText(movie.movieBudget.toString())
            revenueEditText.setText(movie.movieRevenue.toString())
            summaryEditText.setText(movie.movieSummary)


        }


    }
}