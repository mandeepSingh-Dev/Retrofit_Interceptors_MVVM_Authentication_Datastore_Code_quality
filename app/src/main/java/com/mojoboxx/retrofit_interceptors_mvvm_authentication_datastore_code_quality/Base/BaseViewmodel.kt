package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.Base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


open class BaseViewmodel<T>  : ViewModel() {

    var _networkEvent = Channel<T>()
    val event get() = _networkEvent.receiveAsFlow()




}