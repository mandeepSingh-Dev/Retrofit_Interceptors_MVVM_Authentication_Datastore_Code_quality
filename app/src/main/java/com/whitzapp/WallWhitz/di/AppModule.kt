package com.whitzapp.WallWhitz.di

import android.content.Context
import android.net.ConnectivityManager
import com.whitzapp.WallWhitz.common.util.connectivity.ConnectivityState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    fun connectivity_manager_instance(@ApplicationContext context: Context): ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


}