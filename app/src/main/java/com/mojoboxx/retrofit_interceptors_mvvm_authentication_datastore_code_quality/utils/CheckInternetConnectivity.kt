package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

class CheckInternetConnectivity {

    companion object {
        fun isInternet(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetwork!=null
        }
    }

}