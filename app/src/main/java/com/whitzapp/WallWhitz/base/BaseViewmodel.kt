package com.whitzapp.WallWhitz.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


open class BaseViewmodel<T>  : ViewModel() {

    var _networkEvent = Channel<T>()
    val event get() = _networkEvent.receiveAsFlow()




}