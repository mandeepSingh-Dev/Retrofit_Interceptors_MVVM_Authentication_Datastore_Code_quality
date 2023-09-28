package com.whitzapp.WallWhitz.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.whitzapp.WallWhitz.base.BaseViewmodel
import com.whitzapp.WallWhitz.data.remote.model.Hit
import com.whitzapp.WallWhitz.data.remote.model.PixabayResponse
import com.whitzapp.WallWhitz.data.remote.repository_impl.PixabayRepositoryImpl
import com.whitzapp.WallWhitz.domain.use_case.PixabayAllUseCases
import com.whitzapp.WallWhitz.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewmodel @Inject constructor(val pixabayDataRepository: PixabayRepositoryImpl, val pixabayAllUseCases: PixabayAllUseCases): BaseViewmodel<NetworkResult<PixabayResponse>>() {

    val pixabayResponse : Flow<NetworkResult<PixabayResponse>>? = pixabayDataRepository.pixabayResponse

    // Here we using Stateflow for showing list to RecyclerView as it is data holder so
    // when screen rotate it will retain/hold the data in recyclerView
    val hits_list : MutableStateFlow<List<Hit>?> = MutableStateFlow(null)

    var _pixabayUseCaseResponse = MutableStateFlow<NetworkResult<PixabayResponse>?>(null)
    val pixabayUseCaseResponse get() = _pixabayUseCaseResponse.asStateFlow()

    init {

        viewModelScope.launch {
            pixabayDataRepository.getWallpapers(20)

         //   getWallpapers_Category()
        }
    }
    fun getWallpapers(){
        viewModelScope.launch {
            pixabayDataRepository.getWallpapers(20)
        }
    }

    suspend fun getWallpapers_Category(){


        _pixabayUseCaseResponse.value = NetworkResult.Loading()
       val respone =  pixabayAllUseCases.getWallpapers_Category(category = "places")

        when(respone){
            is NetworkResult.Success -> {
                _pixabayUseCaseResponse.value = NetworkResult.Success(data = respone.data)
            }
            is NetworkResult.Error -> {
                _pixabayUseCaseResponse.value = NetworkResult.Error(error = respone.error)
            }
            else -> {}
        }
    }


}