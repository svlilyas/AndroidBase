package com.papirus.androidbase.platform

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context

        /**
         * Used for checking internet connectivity statuses
         * Unavailable, Available, Lost and Losing states
         */
        val connectivityObserver: NetworkConnectivityObserver by lazy {
            NetworkConnectivityObserver(appContext)
        }
    }
}
