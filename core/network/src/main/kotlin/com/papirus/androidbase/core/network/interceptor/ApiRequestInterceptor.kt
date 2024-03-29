package com.papirus.androidbase.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ApiRequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(chain.request())

    private fun addHeaderToRequest(request: Request): Request {
        val newRequest = request.newBuilder()

        /** If you want to add Token or Header value   */
        return newRequest.build()
    }

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val HEADER_COOKIE = "Cookie"
        const val HEADER_VALUE = "header_value"
    }
}
