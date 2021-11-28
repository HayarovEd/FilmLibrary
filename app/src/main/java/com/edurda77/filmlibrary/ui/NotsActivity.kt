package com.edurda77.filmlibrary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edurda77.filmlibrary.databinding.ActivityMainBinding
import com.edurda77.filmlibrary.databinding.ActivityNotsBinding

class NotsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNotsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}