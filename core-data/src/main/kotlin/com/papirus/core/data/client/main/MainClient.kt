package com.papirus.core.data.client.main

import com.papirus.core.model.remote.response.SampleResponse
import com.papirus.core.network.service.MainService
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
