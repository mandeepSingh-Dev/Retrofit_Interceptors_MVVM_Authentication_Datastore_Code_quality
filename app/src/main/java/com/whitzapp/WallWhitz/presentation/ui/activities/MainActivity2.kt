package com.whitzapp.WallWhitz.presentation.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.whitzapp.WallWhitz.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    var _binding : ActivityMain2Binding? = null
    val binding : ActivityMain2Binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
}