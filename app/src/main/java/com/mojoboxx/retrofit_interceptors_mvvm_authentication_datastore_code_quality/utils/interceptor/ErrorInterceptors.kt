package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.interceptor

import android.content.Context
import android.util.Log
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.CheckInternetConnectivity
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class ErrorInterceptors(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val isInternet = CheckInternetConnectivity.isInternet(context)
        if( !isInternet )
        {
              throw IOException("No Connectivity! Please check your connection.")
        }

        val requestBuilder = chain.request().newBuilder()
        return chain.proceed(requestBuilder.build())
    }
}