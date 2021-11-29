package com.edurda77.filmlibrary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.data.NoteMovie
import com.edurda77.filmlibrary.databinding.ActivityNotsBinding
import com.edurda77.filmlibrary.domain.NoteRepo

class NotsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotsBinding
    private val noteRepo: NoteRepo by lazy { app.noteRepo }
    val notsOfMovie = emptyList<NoteMovie>().toMutableList()
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
                override fun onStateClick(note: NoteMovie, position: Int) {
                    Thread {

                        //val iDMovie = goIDMovie.getReposForIDMovieSync(movie)
                        runOnUiThread {
                            val intent = Intent(this@NotsActivity, NoteActivity::class.java)
                            intent.putExtra(NoteMovie::class.java.simpleName, note)

                            startActivity(intent)
                        }
                    }.start()
                }
            }
        recyclerView.adapter = NoteAdapter(notsOfMovie,stateClickListener)
    }

    private fun initNots(): List<NoteMovie> {
        noteRepo.getNots().forEach {
            notsOfMovie.add(it)
        }

        return notsOfMovie
    }
}
