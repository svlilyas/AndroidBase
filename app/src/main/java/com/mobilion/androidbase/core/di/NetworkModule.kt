package com.mobilion.androidbase.core.di

import android.content.Context
import com.mobilion.androidbase.BuildConfig
import com.mobilion.androidbase.core.di.qualifers.*
import com.mobilion.androidbase.core.network.NetworkController
import com.mobilion.androidbase.core.network.*
import com.mobilion.data.remote.ProjectService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideProjectService(
        @ProjectRetrofit projectRetrofit: Retrofit
    ): ProjectService = projectRetrofit.create(ProjectService::class.java)


    @ProjectRetrofit
    @Provides
    fun provideProjectRetrofit(
        @ProjectOkHttpClient projectOkHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder().apply {
        baseUrl(BuildConfig.SERVICE_URL)
        addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        addConverterFactory(GsonConverterFactory.create())
        client(projectOkHttpClient)
    }.build()

    @ProjectOkHttpClient
    @Provides
    fun provideProjectOkHttpClient(
        @DefaultOkHttpClientBuilder okHttpClientBuilder: OkHttpClient.Builder,
        @ApplicationContext context: Context
    ) = okHttpClientBuilder.apply {

        //addInterceptor(apiRequestInterceptor)
        /**
         *  If you want to add SSlPinning Certificate
         *
        val sslPinningApplier = APISSLPinningApplier()
        sslPinningApplier.apply(this, context.assets, sslPinningApplier.getCertificatePaths())
        sslPinningApplier.apply(
            this,
            BuildConfig.SERVICE_URL,
            sslPinningApplier.getSSLPublicKeys()
        )
        //For DEBUG
        sslPinningApplier.trustAllCertificatesForDebug(this)
        */

    }.build()

    @Provides
    @DefaultOkHttpClientBuilder
    fun provideDefaultOkHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(provideLoggingInterceptor())
        .callTimeout(DEFAULT_CALL_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)

    @Provides
    @Singleton
    fun provideNetworkController(
        @ApplicationContext context: Context,
    ) = NetworkController(context)


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

}