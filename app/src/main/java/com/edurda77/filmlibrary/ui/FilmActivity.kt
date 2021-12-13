package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.NoteMovie
import com.edurda77.filmlibrary.databinding.ActivityFilmBinding
import com.edurda77.filmlibrary.domain.NoteDao


class FilmActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var binding: ActivityFilmBinding
    private var beginURL = "https://image.tmdb.org/t/p/w500"
    private val noteDao: NoteDao by lazy { app.noteDao }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()

        val titleEditText: TextView = binding.titleMovie
        val idEditText: TextView = binding.idMovie
        val genreEditText: TextView = binding.ganreMovie
        val durationEditText: TextView = binding.durationMovie
        val yearEditText: TextView = binding.yearMovie
        val budgetEditText: TextView = binding.budgetMovie
        val revenueEditText: TextView = binding.revenueMovie
        val summaryEditText: TextView = binding.summaryMovie
        val popularityEditText: TextView = binding.populatityMovie
        val picture: ImageView = binding.pictureMovie

        val arguments = intent.extras


        val movie: Movie
        if (arguments != null) {
            movie = arguments.getSerializable(Movie::class.java.simpleName) as Movie
            titleEditText.text = movie.title
            idEditText.text = idEditText.text.toString() + movie.id.toString()
            val genre = StringBuilder(" ")
            movie.movieGenre.forEach {
                genre.append(it.name).append(", ")
            }

            genreEditText.text = genreEditText.text.toString() + genre
            yearEditText.text = yearEditText.text.toString() + movie.releaseDate
            durationEditText.text = durationEditText.text.toString() + movie.runtime.toString()
            budgetEditText.text = budgetEditText.text.toString() + movie.budget.toString()
            revenueEditText.text = revenueEditText.text.toString() + movie.revenue.toString()
            summaryEditText.text = summaryEditText.text.toString() + movie.overview
            popularityEditText.text =
                popularityEditText.text.toString() + movie.popularity.toString()


            Glide.with(this).load(beginURL + movie.posterPath)
                .override(320, 480)
                .placeholder(R.drawable.video).into(picture)
            binding.saveNots.setOnClickListener {

                    val id = movie.id
                    val title = movie.title
                    val contentNote = binding.noteMovie.text.toString()
                    val note = NoteMovie(id, title, contentNote)
                Thread {
                    noteDao.add(note)
                }.start()
                Toast.makeText(this,"Заметка добавлена", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun setToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemMenu = MenuDelegate(item)
        itemMenu.setMenu(this,item)
        return super.onOptionsItemSelected(item)
    }
}