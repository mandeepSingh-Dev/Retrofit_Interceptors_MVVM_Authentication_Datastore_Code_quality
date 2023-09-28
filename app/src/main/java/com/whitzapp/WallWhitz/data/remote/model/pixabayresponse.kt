package com.whitzapp.WallWhitz.data.remote.model

data class PixabayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)