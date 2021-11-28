package com.edurda77.filmlibrary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.data.Movie
import com.edurda77.filmlibrary.data.NotsMovie
import com.edurda77.filmlibrary.data.ResultSearchMovie
import com.edurda77.filmlibrary.databinding.ActivityMainBinding
import com.edurda77.filmlibrary.databinding.ActivityNotsBinding

class NotsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNotsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setRecycledView()
    }

    private fun setRecycledView() {
        val recyclerView: RecyclerView = binding.notsRecycledView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val notsOfMovie=initNots()
        val stateClickListener: NoteAdapter.OnStateClickListener =
            object : NoteAdapter.OnStateClickListener {
                override fun onStateClick(note: NotsMovie, position: Int) {
                    Thread {
                        //val iDMovie = goIDMovie.getReposForIDMovieSync(movie)
                        runOnUiThread {
                            val intent = Intent(this@NotsActivity, NoteActivity::class.java)
                            intent.putExtra(Movie::class.java.simpleName, note)

                            startActivity(intent)
                        }
                    }.start()
                }
            }
        recyclerView.adapter = NoteAdapter(notsOfMovie,stateClickListener)
    }

    private fun initNots(): List<NotsMovie> {
        val notsOfMovie = emptyList<NotsMovie>().toMutableList()
        notsOfMovie.add(NotsMovie(12,"ffff", "ffff"))
        notsOfMovie.add(NotsMovie(13,"ffdfdff", "fdfffff"))
        notsOfMovie.add(NotsMovie(14,"aaa", "ffhhhhff"))
        return notsOfMovie
    }
}
