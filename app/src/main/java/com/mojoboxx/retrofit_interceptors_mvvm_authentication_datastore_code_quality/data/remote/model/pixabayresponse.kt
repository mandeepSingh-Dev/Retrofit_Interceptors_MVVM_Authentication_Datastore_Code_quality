package com.mojoboxx.retrofit_interceptors_mvvm_authentication_datastore_code_quality.data.remote.model

data class PixabayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)