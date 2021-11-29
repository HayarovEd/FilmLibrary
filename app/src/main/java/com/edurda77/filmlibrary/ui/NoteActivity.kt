package com.edurda77.filmlibrary.ui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.edurda77.filmlibrary.data.NoteMovie
import com.edurda77.filmlibrary.databinding.ActivityNoteBinding
import com.edurda77.filmlibrary.domain.NoteRepo

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    private val noteRepo: NoteRepo by lazy { app.noteRepo }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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
                note.copy(id,title,content)
                noteRepo.update(id,note)
                initStartActivity()
            }
            binding.deleteNote.setOnClickListener {
                note.copy(id,title,content)
                noteRepo.delete(id)
                initStartActivity()
            }
        }


    }
    fun initStartActivity(){
        val intent = Intent(this@NoteActivity, NotsActivity::class.java)
        startActivity(intent)
    }
}