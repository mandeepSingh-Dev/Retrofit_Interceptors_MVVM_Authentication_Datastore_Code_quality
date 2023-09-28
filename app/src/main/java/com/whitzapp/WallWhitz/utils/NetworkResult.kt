package com.whitzapp.WallWhitz.utils

sealed class NetworkResult<T> ( data:T? , message: String?, error: String?) {

    class Loading<T> : NetworkResult<T>(null,null, null)
    class Success<T>(val data: T?,val message:String? = null) : NetworkResult<T>(data = data, message = message, error = null)
    class Error<T>(val error : String?) : NetworkResult<T>(null,null, error = error)
}