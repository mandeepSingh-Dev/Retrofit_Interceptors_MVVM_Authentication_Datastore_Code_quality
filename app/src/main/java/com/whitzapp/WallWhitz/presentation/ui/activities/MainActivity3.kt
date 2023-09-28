package com.whitzapp.WallWhitz.presentation.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.whitzapp.WallWhitz.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    var _binding : ActivityMain3Binding? = null
    val binding : ActivityMain3Binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMain3Binding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.button.setOnClickListener {

            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }
    }
}