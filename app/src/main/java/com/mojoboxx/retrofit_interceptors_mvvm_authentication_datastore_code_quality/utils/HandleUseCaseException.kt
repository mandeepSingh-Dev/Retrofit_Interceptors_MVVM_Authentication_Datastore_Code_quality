package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils

import android.media.MediaDrm.ErrorCodes
import android.net.http.HttpException
import android.net.http.NetworkException
import android.util.Log
import androidx.annotation.RequiresApi
import okhttp3.internal.http2.ErrorCode
import okio.IOException
import retrofit2.http.HTTP
import java.net.HttpURLConnection

fun handleUseCaseException(e: Exception) : String{
       return when(e){
            is retrofit2.HttpException -> {
                networkCodeError(e)
            }
            is IOException -> {
               e.message.toString()
                /// implement code to show snackbar from here for internet connection
            }
           else -> {
               e.message.toString()
           }

        }
}

fun networkCodeError(exception : retrofit2.HttpException): String{

   return when(exception.code()){
        HttpURLConnection.HTTP_BAD_REQUEST ->  "400 : Client Error! Bad Request"
        HttpURLConnection.HTTP_UNAUTHORIZED -> "Token is Expired,login again!"
        HttpURLConnection.HTTP_PAYMENT_REQUIRED -> "Payment Required"
        else -> {exception.message.toString()}
    }

}