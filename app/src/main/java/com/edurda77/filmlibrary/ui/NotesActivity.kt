package com.edurda77.filmlibrary.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.NoteMovie
import com.edurda77.filmlibrary.databinding.ActivityNotesBinding
import com.edurda77.filmlibrary.domain.NoteDao

class NotesActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var binding: ActivityNotesBinding
    private val noteDao: NoteDao by lazy { app.noteDao }
    private val notesOfMovie = emptyList<NoteMovie>().toMutableList()
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
        val notesOfMovie=initNotes()
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
        recyclerView.adapter = NoteAdapter(notesOfMovie,stateClickListener)
    }

    private fun initNotes(): List<NoteMovie> {
        Thread{
        noteDao.getNotes().forEach {
            notesOfMovie.add(it)}
        }.start()

        return notesOfMovie
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
