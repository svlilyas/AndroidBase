package com.papirus.androidbase.core.network.interceptor

import com.papirus.androidbase.core.network.annotation.Authenticated
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

class AuthenticationInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let { request ->
        val invocation =
            chain.request().tag(Invocation::class.java) ?: return chain.proceed(chain.request())

        val shouldAttachAuthHeader =
            invocation.method().annotations.any { it1 -> it1.annotationClass == Authenticated::class }

        /**
         * Adding Queries to Url
         */
        val url = request.url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("format", "json")
            .build()

        val newRequest = request.newBuilder()
            .url(url)

        /**
         * Adding Header
         */
        if (shouldAttachAuthHeader) {
            newRequest.addHeader("Authorization", "...token...")
        }

        chain.proceed(newRequest.build())
    }
}
