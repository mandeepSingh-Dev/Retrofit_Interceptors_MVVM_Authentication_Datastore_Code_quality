package com.whitzapp.WallWhitz.presentation.ui.activities

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.RotateDrawable
import android.media.AudioManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.MenuHost
import androidx.core.view.children
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import com.whitzapp.WallWhitz.base.BaseActivity
import com.whitzapp.WallWhitz.R
import com.whitzapp.WallWhitz.common.util.connectivity.ConnectivityState
import com.whitzapp.WallWhitz.databinding.ActivityMainAnimationBinding
import com.whitzapp.WallWhitz.databinding.ActivityMainBinding
import com.whitzapp.WallWhitz.utils.views.BottomNavigationIndicator
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : BaseActivity()  {

    var _binding : ActivityMainBinding? = null
    val binding : ActivityMainBinding get() = _binding!!

    var _binding2 : ActivityMainAnimationBinding? = null
    val binding2 : ActivityMainAnimationBinding get() = _binding2!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        _binding2 = ActivityMainAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)





        Log.d("dkmnkdncd",resources.displayMetrics.widthPixels.toString())
        Log.d("dkmnkdncd",resources.displayMetrics.heightPixels.toString())

        val heightDP = resources.displayMetrics.heightPixels/resources.displayMetrics.density
        val heightPX = heightDP*resources.displayMetrics.density

        Log.d("dkmnkdncd",heightDP.toString() + "  DP VALUE")
        Log.d("dkmnkdncd",heightPX.toString() + "  PX VALUE" )

        val snackbar = Snackbar.make(binding.root,"No Internet Available",Snackbar.LENGTH_LONG)


        val snackbarlaout = snackbar.view as SnackbarLayout

       // snackbarlaout.addView(binding.button)
        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)





        lifecycleScope.launch {
            mConnectivityManager.connectivityState.collect{
                Log.d("dkcnkcmd",it.name+"  ->")
                if(it == ConnectivityState.AVAILABLE){
                    snackbar.setText("Internet Available")
                    snackbar.show()
                }else{
                    snackbar.setText("No Internet Available")
                    snackbar.show()
                }
            }
        }










        binding.button.post {
            val bitmap = Bitmap.createBitmap(
                binding.button.measuredWidth,
                binding.button.measuredHeight,
                Bitmap.Config.ARGB_8888
            )

            val bitmap3 = BitmapFactory.decodeResource(resources, R.drawable.loadingimage)
            val bitmap2 = bitmap3.copy(Bitmap.Config.ARGB_8888,true)


            val canvas = Canvas(bitmap2)

            val paint = Paint()
            paint.color = Color.RED
            paint.textAlign = Paint.Align.CENTER
            paint.textSize = 24f
            paint.isUnderlineText = true

            canvas.drawText("Hello",0f,0f,paint)

            val rAnimation = RotateAnimation(0f,360f)
            rAnimation.duration = 1000
            rAnimation.repeatMode = Animation.REVERSE
            rAnimation.fillAfter = true

            binding.button.animation = rAnimation
           // binding.button.animate()
            rAnimation.start()


          /*  CoroutineScope(Dispatchers.Main).launch {
                (0..3960).forEach {
                    binding.imageview.rotation = it.toFloat()
                    delay(10)
                }

            }*/



           // binding.button.draw(canvas)


         //   canvas.drawBitmap(bitmap, 100f, 100f, null)

            binding.imageview.load(bitmap2)
        }





        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL

        var set = true

        binding.button.setOnClickListener {

          /*  val transition = ChangeBounds()
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
*/

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }



}