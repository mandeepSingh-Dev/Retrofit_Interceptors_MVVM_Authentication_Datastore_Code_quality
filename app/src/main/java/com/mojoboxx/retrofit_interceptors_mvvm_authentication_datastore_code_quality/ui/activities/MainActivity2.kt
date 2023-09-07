package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.R
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.databinding.ActivityMain2Binding
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.databinding.ActivityMainBinding

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