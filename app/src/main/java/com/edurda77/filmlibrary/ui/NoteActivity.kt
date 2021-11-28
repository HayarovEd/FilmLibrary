package com.edurda77.filmlibrary.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.edurda77.filmlibrary.data.NotsMovie
import com.edurda77.filmlibrary.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val titleText: TextView? = binding.titleMovieNote
        val contentEditText: EditText? = binding.noteChangeMovie


        val arguments = intent.extras
        val note: NotsMovie

        if (arguments != null) {
            note = arguments.getSerializable(NotsMovie::class.java.simpleName) as NotsMovie

            var content = note.contentNote
            val title = note.titleNote
            val id = note.idNote
            titleText?.text = title
            contentEditText?.setText(content)
            content = binding.noteChangeMovie.text.toString()
            binding.saveChangeNots.setOnClickListener {
                note.copy(id,title,content)
            }
        }


    }
}