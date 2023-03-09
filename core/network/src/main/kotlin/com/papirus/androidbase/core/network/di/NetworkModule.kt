package com.papirus.androidbase.core.network.di

import com.papirus.androidbase.core.network.BuildConfig
import com.papirus.androidbase.core.network.interceptor.ApiRequestInterceptor
import com.papirus.androidbase.core.network.interceptor.AuthenticationInterceptor
import com.papirus.androidbase.core.network.interceptor.HttpRequestInterceptor
import com.papirus.androidbase.core.network.interceptor.UserAgentInterceptor
import com.papirus.androidbase.core.network.service.MainService
import com.papirus.androidbase.core.network.utils.DEFAULT_CALL_TIMEOUT_MILLIS
import com.papirus.androidbase.core.network.utils.DEFAULT_CONNECT_TIMEOUT_MILLIS
import com.papirus.androidbase.core.network.utils.DEFAULT_READ_TIMEOUT_MILLIS
import com.papirus.androidbase.core.network.utils.DEFAULT_WRITE_TIMEOUT_MILLIS
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .addInterceptor(
                provideAuthenticationInterceptor()
            )
            .addInterceptor(provideUserAgentInterceptor())
            .addInterceptor(
                provideApiRequestInterceptor()
            )
            .callTimeout(DEFAULT_CALL_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.SERVICE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideAuthenticationInterceptor(): AuthenticationInterceptor =
        AuthenticationInterceptor(BuildConfig.SERVICE_API_KEY)

    @Provides
    @Singleton
    fun provideApiRequestInterceptor(): ApiRequestInterceptor =
        ApiRequestInterceptor()

    @Provides
    @Singleton
    fun provideUserAgentInterceptor(): UserAgentInterceptor =
        UserAgentInterceptor()
}
