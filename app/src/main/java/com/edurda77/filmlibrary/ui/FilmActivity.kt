package com.edurda77.filmlibrary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.domain.Movie

class FilmActivity : AppCompatActivity() {
    private var titlEditText: EditText? = null
    private var ganreEditText: EditText? = null
    private var durationEditText: EditText? = null
    private var rangEditText: EditText? = null
    private var yearEditText: EditText? = null
    private var budgetEditText: EditText? = null
    private var revenueEditText: EditText? = null
    private var summaryEditText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        titlEditText = findViewById(R.id.title_movie)
        ganreEditText = findViewById(R.id.ganre_movie)
        durationEditText = findViewById(R.id.duration_movie)
        rangEditText = findViewById(R.id.rang_movie)
        yearEditText = findViewById(R.id.year_movie)
        budgetEditText = findViewById(R.id.budget_movie)
        revenueEditText = findViewById(R.id.revenue_movie)
        summaryEditText = findViewById(R.id.summary_movie)
        val arguments = intent.extras

        val movie: Movie
        if (arguments != null) {
            movie = arguments.getSerializable(Movie::class.java.getSimpleName()) as Movie
            titlEditText?.setText(movie.movieTitle)
            rangEditText?.setText(movie.movieRang.toString())
            ganreEditText?.setText(movie.movieGanre)
            yearEditText?.setText(movie.movieYear)
            durationEditText?.setText(movie.movieDuration)
            budgetEditText?.setText(movie.movieBudget.toString())
            revenueEditText?.setText(movie.movieRevenue.toString())
            summaryEditText?.setText(movie.movieSummary)


        }


    }
}