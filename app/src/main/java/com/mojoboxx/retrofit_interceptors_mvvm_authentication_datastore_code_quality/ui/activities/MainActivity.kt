package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.ui.activities

import android.content.Intent
import android.media.AudioManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.viewbinding.BuildConfig
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.Base.BaseActivity
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.R
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.databinding.ActivityMain2Binding
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.databinding.ActivityMainAnimationBinding
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.databinding.ActivityMainBinding
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.views.LoadingBarDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    var _binding : ActivityMainBinding? = null
    val binding : ActivityMainBinding get() = _binding!!

    var _binding2 : ActivityMainAnimationBinding? = null
    val binding2 : ActivityMainAnimationBinding get() = _binding2!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        _binding2 = ActivityMainAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL

        var set = true

        binding.button.setOnClickListener {

            val transition = ChangeBounds()
           // transition.interpolator = BounceInterpolator()
            transition.duration = 500

            if(set) {
                val constraintSet = ConstraintSet()
                constraintSet.clone(this,R.layout.activity_main_animation)

                constraintSet.applyTo(binding.root)
                TransitionManager.beginDelayedTransition(binding.root,transition)
                set = false
            }else{
                val constraintSet = ConstraintSet()
                constraintSet.clone(this,R.layout.activity_main)

                constraintSet.applyTo(binding.root)

                TransitionManager.beginDelayedTransition(binding.root, transition)
                set = true
            }

           // val intent = Intent(this,MainActivity2::class.java)
          //  startActivity(intent)
        }


    }
}