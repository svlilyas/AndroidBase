package com.mobilion.androidbase.core.platform

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.multidex.MultiDex
import com.mobilion.androidbase.core.network.NetworkUnavailableException
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProjectApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        injectMultiDex()
    }

    private fun injectMultiDex(){
        MultiDex.install(this.applicationContext)
    }

    companion object{
        lateinit var appContext: Context

        /**
         * Used for checking internet connectivity. Main usage is on [ProjectApiRequestInterceptor]
         */

        val networkStatusObservable: MutableLiveData<NetworkUnavailableException> by lazy {
            MutableLiveData()
        }

    }
}