package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.data.remote.repository

import android.net.http.HttpException
import android.util.Log
import androidx.viewbinding.BuildConfig
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.data.remote.ApiServices.PixabayServices
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.data.remote.model.PixabayResponse
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.NetworkResult
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.handleUseCaseException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import java.net.HttpURLConnection
import javax.inject.Inject

class PixabayDataRepository @Inject constructor(val pixabayServices: PixabayServices){


    //Here we using Channel so that it can observe only once so when screen rotate it will not observe again like Stateflow
    var _pixabayResponse : Channel<NetworkResult<PixabayResponse>>? = Channel<NetworkResult<PixabayResponse>>()
    val pixabayResponse  get() =  _pixabayResponse?.receiveAsFlow()

    suspend fun getWallpapers(){

        try{
            _pixabayResponse?.send(NetworkResult.Loading())

            val response = pixabayServices.getWallpapers()

            if(response.isSuccessful) {
                _pixabayResponse?.send(NetworkResult.Success(data = response.body()))

                response.headers().forEach {
                    Log.d("dkmkdvmd", "${it.first} - ${it.second} HEADERS")
                    Log.d("dkmkdvmd",response.code().toString())
                    Log.d("dkmkdvmd",response.message().toString())
                }
            }else{
                 val networkException = retrofit2.HttpException(response)
                Log.d("dknkd",response.code().toString())
                _pixabayResponse?.send(NetworkResult.Error(handleUseCaseException(networkException)))
            }
        }catch (e:Exception){
            Log.d("dkmkdvmd", e.message.toString())
            _pixabayResponse?.send(NetworkResult.Error(handleUseCaseException(e).toString()))
        }

    }

}