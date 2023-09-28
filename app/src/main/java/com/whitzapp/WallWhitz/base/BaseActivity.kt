package com.whitzapp.WallWhitz.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.whitzapp.WallWhitz.common.util.connectivity.ConnectivityState
import com.whitzapp.WallWhitz.common.util.connectivity.MConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var mConnectivityManager: MConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mConnectivityManager.register()



    }

    override fun onDestroy() {
        super.onDestroy()
        mConnectivityManager.unregister()
    }

}