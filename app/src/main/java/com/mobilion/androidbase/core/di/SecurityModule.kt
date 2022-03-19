package com.mobilion.androidbase.core.di

import android.content.Context
import com.mobilion.androidbase.core.common.ProjectSecureSharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {

    @Provides
    @Singleton
    fun provideProjectSecureSharedPref(
        @ApplicationContext context: Context
    ) = ProjectSecureSharedPref(context)

}
