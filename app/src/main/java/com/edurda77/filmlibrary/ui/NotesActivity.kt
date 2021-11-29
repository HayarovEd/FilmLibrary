package com.edurda77.filmlibrary.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.NoteMovie
import com.edurda77.filmlibrary.databinding.ActivityNotesBinding
import com.edurda77.filmlibrary.domain.NoteDao
import com.edurda77.filmlibrary.domain.NoteRepo

class NotesActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var binding: ActivityNotesBinding
    private val noteDao: NoteDao by lazy { app.noteDao }
    val notsOfMovie = emptyList<NoteMovie>().toMutableList()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNotesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
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
                            val intent = Intent(this@NotesActivity, NoteActivity::class.java)
                            intent.putExtra(NoteMovie::class.java.simpleName, note)

                            startActivity(intent)
                        }
                    }.start()
                }
            }
        recyclerView.adapter = NoteAdapter(notsOfMovie,stateClickListener)
    }

    private fun initNots(): List<NoteMovie> {
        noteDao.getNots().forEach {
            notsOfMovie.add(it)
        }

        return notsOfMovie
    }
    private fun setToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.nots -> {
                val intent = Intent(this, NotesActivity::class.java)
                startActivity(intent)
            }
            R.id.custom -> {
                val intent = Intent(this, CustomActivity::class.java)
                startActivity(intent)
            }
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
