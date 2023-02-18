package com.papirus.core.network.service

import com.papirus.core.model.remote.response.SampleResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface MainService {
    object Endpoint {
        const val mainPath = "/Sample"

        object Sample {
            const val samplePath = "${mainPath}/products"
        }
    }

    @GET(Endpoint.Sample.samplePath)
    suspend fun fetchSampleInfo(name: String): ApiResponse<SampleResponse>
}
