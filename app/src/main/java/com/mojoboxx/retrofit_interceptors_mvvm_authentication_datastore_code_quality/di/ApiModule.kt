package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.di

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.data.remote.ApiServices.PixabayServices
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.Constants
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.interceptor.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideErrorInterceptor(@ApplicationContext context: Context) = ErrorInterceptors(context)



    @Provides
    fun provideCacheInterceptor() = CacheInterceptor()

    @Provides
    fun provideForceCacheInterceptor(@ApplicationContext context: Context) = ForceCacheInterceptor(context)

    @Provides
    fun provideOnlineCacheInterceptor() =  OnlineInterceptor()

    @Provides
    fun provideOfflineCacheInterceptor(@ApplicationContext context: Context) = OfflineInterceptor(context)

    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheSize = (10*1024*1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)

        return cache
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, errorInterceptors: ErrorInterceptors,cacheInterceptor: CacheInterceptor,forceCacheInterceptor: ForceCacheInterceptor,offlineInterceptor: OfflineInterceptor,onlineInterceptor: OnlineInterceptor,cache: Cache, @ApplicationContext context: Context) = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
     //   .addInterceptor(errorInterceptors)
        .addInterceptor(offlineInterceptor)
        .addNetworkInterceptor(onlineInterceptor)
        .pingInterval(2000,TimeUnit.DAYS)
        .connectionPool(ConnectionPool())
        .cache(cache)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun providePixabayService(retrofit: Retrofit) = retrofit.create(PixabayServices::class.java)

}