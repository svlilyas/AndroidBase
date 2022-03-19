package com.mobilion.data.remote

import com.mobilion.data.remote.request.LoginRequest
import com.mobilion.data.remote.response.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ProjectService {
    object Endpoint {
        object Authentication {
            const val mainPath = "/Account/"
            const val login = "$mainPath/GetloginWithProfileSummaryV3"
        }
    }

    @GET(Endpoint.Authentication.login)
    fun fetchLogin(
        @Query("gsm") gsm: String,
        @Query("password") password: String,
    ): Single<LoginResponse>

}
