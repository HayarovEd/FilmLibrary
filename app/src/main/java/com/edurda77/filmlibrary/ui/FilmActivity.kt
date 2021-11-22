package com.edurda77.filmlibrary.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.databinding.ActivityFilmBinding

class FilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val titlEditText: TextView? = binding.titleMovie
        val idEditText: TextView? = binding.idMovie
        val ganreEditText: TextView? = binding.ganreMovie
        val durationEditText: TextView? = binding.durationMovie
        val rangEditText: TextView? = binding.rangMovie
        val yearEditText: TextView? = binding.yearMovie
        val budgetEditText: TextView? = binding.budgetMovie
        val revenueEditText: TextView? = binding.revenueMovie
        val summaryEditText: TextView? = binding.summaryMovie
        val popularityEditText: TextView? = binding.populatityMovie
        val arguments = intent.extras

        val movie: Movie
        if (arguments != null) {
            movie = arguments.getSerializable(Movie::class.java.getSimpleName()) as Movie
            titlEditText?.setText(movie.title)
            idEditText?.setText(movie.id.toString())
            rangEditText?.setText(movie.popularity.toString())
            ganreEditText?.setText(movie.movieGanre)
            yearEditText?.setText(movie.releaseDate)
            durationEditText?.setText(movie.runtime.toString())
            budgetEditText?.setText(movie.budget.toString())
            revenueEditText?.setText(movie.revenue.toString())
            summaryEditText?.setText(movie.overview)
            popularityEditText?.setText(movie.popularity.toString())



        }


    }
}