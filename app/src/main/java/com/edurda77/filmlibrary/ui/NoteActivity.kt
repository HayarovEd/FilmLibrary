package com.edurda77.filmlibrary.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.NoteMovie
import com.edurda77.filmlibrary.databinding.ActivityNoteBinding
import com.edurda77.filmlibrary.domain.NoteDao

class NoteActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var binding: ActivityNoteBinding
    private val noteDao: NoteDao by lazy { app.noteDao }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        val titleText: TextView = binding.titleMovieNote
        val contentEditText: EditText = binding.noteChangeMovie


        val arguments = intent.extras
        val note: NoteMovie

        if (arguments != null) {
            note = arguments.getSerializable(NoteMovie::class.java.simpleName) as NoteMovie

            var content = note.contentNote
            val title = note.titleNote
            val id = note.idNote
            titleText.text = title
            contentEditText.setText(content)


            binding.saveChangeNots.setOnClickListener {
                content = binding.noteChangeMovie.text.toString()
                Toast.makeText(this,content,Toast.LENGTH_LONG).show()
                Thread {
                    noteDao.update(id,  content)
                    runOnUiThread {
                        initStartActivity()
                    }
                }.start()
                Toast.makeText(this,"Заметка обновлена",Toast.LENGTH_SHORT).show()
            }
            binding.deleteNote.setOnClickListener {


                Thread {
                    noteDao.delete(id)
                    runOnUiThread {
                        initStartActivity()
                    }

                }.start()
                Toast.makeText(this,"Заметка удалена",Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun initStartActivity() {
        val intent = Intent(this@NoteActivity, NotesActivity::class.java)
        startActivity(intent)
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
        when (item.itemId) {
            R.id.start -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.people_search -> {
                val intent = Intent(this, SearchPeopleActivity::class.java)
                startActivity(intent)
            }
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