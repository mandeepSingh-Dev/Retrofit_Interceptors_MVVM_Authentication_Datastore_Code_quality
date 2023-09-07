package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.data.remote.ApiServices

import androidx.annotation.Keep
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.BuildConfig
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.data.remote.model.PixabayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface PixabayServices {

    @GET("api/")
    suspend fun getWallpapers(@Query("key") key :String = BuildConfig.PIXABAY_KEY, @Query("per_page") per_page:Int = 150) : Response<PixabayResponse>

}