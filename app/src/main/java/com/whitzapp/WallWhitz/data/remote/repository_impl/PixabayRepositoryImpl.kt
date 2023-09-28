package com.whitzapp.WallWhitz.data.remote.repository_impl

import android.util.Log
import com.whitzapp.WallWhitz.data.remote.ApiServices.PixabayServices
import com.whitzapp.WallWhitz.data.remote.model.PixabayResponse
import com.whitzapp.WallWhitz.domain.repository.PixabayRepository
import com.whitzapp.WallWhitz.utils.NetworkResult
import com.whitzapp.WallWhitz.utils.handleUseCaseException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import retrofit2.HttpException
import javax.inject.Inject

class PixabayRepositoryImpl @Inject constructor(val pixabayServices: PixabayServices) :
    PixabayRepository {


    //Here we using Channel so that it can observe only once so when screen rotate it will not observe again like Stateflow
    var _pixabayResponse : Channel<NetworkResult<PixabayResponse>>? = Channel<NetworkResult<PixabayResponse>>()
    val pixabayResponse  get() =  _pixabayResponse?.receiveAsFlow()

    override suspend fun getWallpapers(per_page : Int) : NetworkResult<PixabayResponse>{

        try{
            _pixabayResponse?.send(NetworkResult.Loading())

            val response = pixabayServices.getWallpapers(per_page)

            if(response.isSuccessful) {
                _pixabayResponse?.send(NetworkResult.Success(data = response.body()))

                response.headers().forEach {
                    Log.d("dkmkdvmd", "${it.first} - ${it.second} HEADERS")
                    Log.d("dkmkdvmd",response.code().toString())
                    Log.d("dkmkdvmd",response.message().toString())
                }

                return NetworkResult.Success(response.body())

            }else{
                 val networkException = retrofit2.HttpException(response)

                _pixabayResponse?.send(NetworkResult.Error(handleUseCaseException(networkException)))

                return NetworkResult.Error(handleUseCaseException(networkException))
            }
        }catch (e:Exception){
            Log.d("dkmkdvmd", e.message.toString())
            _pixabayResponse?.send(NetworkResult.Error(handleUseCaseException(e).toString()))

            return NetworkResult.Error(handleUseCaseException(e))

        }

    }

    override suspend fun getWallpapers_Category(category : String): NetworkResult<PixabayResponse> {
        return try{
           val response = pixabayServices.getWallpapers_Category(category = category)

            if(response.isSuccessful){
               NetworkResult.Success(response.body())
            }else{
                val networkException = HttpException(response)
                NetworkResult.Error(error = handleUseCaseException(networkException))
            }
        }catch (e:Exception){
            NetworkResult.Error(handleUseCaseException(e))
        }
    }

}