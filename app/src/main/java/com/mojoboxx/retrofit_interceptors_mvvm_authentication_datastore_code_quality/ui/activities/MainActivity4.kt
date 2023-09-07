package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.R
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.databinding.ActivityMain3Binding
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.databinding.ActivityMain4Binding
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.databinding.ActivityMainBinding

class MainActivity4 : AppCompatActivity() {


    var _binding : ActivityMain4Binding? = null
    val binding : ActivityMain4Binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMain4Binding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.button.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  )
            startActivity(intent)
        }
    }
}