package com.whitzapp.WallWhitz.data.remote.ApiServices

import com.whitzapp.WallWhitz.BuildConfig
import com.whitzapp.WallWhitz.data.remote.model.PixabayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayServices {

    @GET("api/?key=${BuildConfig.PIXABAY_KEY}")
    suspend fun getWallpapers( @Query("per_page") per_page:Int = 20) : Response<PixabayResponse>

    @GET("api/?key=${BuildConfig.PIXABAY_KEY}")
    suspend fun getWallpapers_Category( @Query("per_page") per_page:Int = 20, @Query("category") category : String) : Response<PixabayResponse>




}