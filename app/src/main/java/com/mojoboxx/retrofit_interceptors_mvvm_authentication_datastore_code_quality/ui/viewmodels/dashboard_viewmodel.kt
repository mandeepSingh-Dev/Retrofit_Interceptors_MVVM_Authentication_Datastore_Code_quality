package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.ui.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.Base.BaseViewmodel
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.data.remote.model.Hit
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.data.remote.model.PixabayResponse
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.data.remote.repository.PixabayDataRepository
import com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewmodel @Inject constructor(val pixabayDataRepository: PixabayDataRepository): BaseViewmodel<NetworkResult<PixabayResponse>>() {

    val pixabayResponse : Flow<NetworkResult<PixabayResponse>>? = pixabayDataRepository.pixabayResponse

    // Here we using Stateflow for showing list to RecyclerView as it is data holder so
    // when screen rotate it will retain/hold the data in recyclerView
    val hits_list : MutableStateFlow<List<Hit>?> = MutableStateFlow(null)

    init {

        viewModelScope.launch {
            pixabayDataRepository.getWallpapers()
        }
    }

    fun getWallpapers(){
        viewModelScope.launch {
            pixabayDataRepository.getWallpapers()
        }
    }


}