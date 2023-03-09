package com.papirus.androidbase.core.data.client.main

import com.papirus.androidbase.core.model.remote.response.SampleResponse
import com.papirus.androidbase.core.network.service.MainService
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MainClient @Inject constructor(
    private val mainService: MainService
) {

    suspend fun fetchSampleInfo(
        name: String
    ): ApiResponse<SampleResponse> =
        mainService.fetchSampleInfo(
            name = name
        )
}
