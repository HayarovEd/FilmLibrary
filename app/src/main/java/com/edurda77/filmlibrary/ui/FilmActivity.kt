package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.NoteMovie
import com.edurda77.filmlibrary.databinding.ActivityFilmBinding
import com.edurda77.filmlibrary.domain.NoteRepo

class FilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmBinding
    private var beginURL = "https://image.tmdb.org/t/p/w500"
    private val noteRepo: NoteRepo by lazy { app.noteRepo }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val titlEditText: TextView = binding.titleMovie
        val idEditText: TextView? = binding.idMovie
        val ganreEditText: TextView? = binding.ganreMovie
        val durationEditText: TextView? = binding.durationMovie
        val yearEditText: TextView? = binding.yearMovie
        val budgetEditText: TextView? = binding.budgetMovie
        val revenueEditText: TextView? = binding.revenueMovie
        val summaryEditText: TextView? = binding.summaryMovie
        val popularityEditText: TextView? = binding.populatityMovie
        val picture : ImageView = binding.pictureMovie

        val arguments = intent.extras


        val movie: Movie
        if (arguments != null) {
            movie = arguments.getSerializable(Movie::class.java.simpleName) as Movie
            titlEditText.text = movie.title
            idEditText?.text = idEditText?.text.toString() + movie.id.toString()

            ganreEditText?.text = ganreEditText?.text.toString() + movie.movieGanre
            yearEditText?.text = yearEditText?.text.toString() + movie.releaseDate
            durationEditText?.text =  durationEditText?.text.toString() + movie.runtime.toString()
            budgetEditText?.text = budgetEditText?.text.toString() + movie.budget.toString()
            revenueEditText?.text = revenueEditText?.text.toString() + movie.revenue.toString()
            summaryEditText?.text = summaryEditText?.text.toString() + movie.overview
            popularityEditText?.text = popularityEditText?.text.toString() + movie.popularity.toString()
            //val url: String  = beginURL+movie.posterPath

            Glide.with(this).load(beginURL+movie.posterPath)
                .override(320, 480)
                .placeholder(R.drawable.video).into(picture)
            binding.saveNots.setOnClickListener {
                val id = movie.id
                val title = movie.title
                val contentNote = binding.noteMovie.text.toString()
                val note = NoteMovie(id, title,contentNote)
                noteRepo.add(note)
            }
        }





    }
}