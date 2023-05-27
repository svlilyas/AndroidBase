package com.papirus.androidbase.initializer

import android.content.Context
import androidx.startup.Initializer
import com.papirus.androidbase.BuildConfig
import com.papirus.androidbase.logging.CrashReportingTree
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("TimberInitializer is initialized.")
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
