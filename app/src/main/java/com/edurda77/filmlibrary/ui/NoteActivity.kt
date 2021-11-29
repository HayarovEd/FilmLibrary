package com.edurda77.filmlibrary.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.NoteMovie
import com.edurda77.filmlibrary.databinding.ActivityNoteBinding
import com.edurda77.filmlibrary.domain.NoteDao
import com.edurda77.filmlibrary.domain.NoteRepo

class NoteActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var binding: ActivityNoteBinding
    private val noteDao: NoteDao by lazy { app.noteDao }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        val titleText: TextView? = binding.titleMovieNote
        val contentEditText: EditText? = binding.noteChangeMovie


        val arguments = intent.extras
        val note: NoteMovie

        if (arguments != null) {
            note = arguments.getSerializable(NoteMovie::class.java.simpleName) as NoteMovie

            var content = note.contentNote
            val title = note.titleNote
            val id = note.idNote
            titleText?.text = title
            contentEditText?.setText(content)
            content = binding.noteChangeMovie.text.toString()
            binding.saveChangeNots.setOnClickListener {

                    note.copy(id, title, content)
                Thread {
                    noteDao.update(id, title, content)
                    runOnUiThread {
                        initStartActivity()
                    }
                }.start()
            }
            binding.deleteNote.setOnClickListener {

                    note.copy(id, title, content)
                Thread {
                    noteDao.delete(id)
                    runOnUiThread {
                        initStartActivity()
                    }

                }.start()
            }
        }


    }

    fun initStartActivity() {
        val intent = Intent(this@NoteActivity, NotesActivity::class.java)
        startActivity(intent)
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