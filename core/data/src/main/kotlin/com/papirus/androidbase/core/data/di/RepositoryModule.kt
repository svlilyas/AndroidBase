package com.papirus.androidbase.core.data.di

import com.papirus.androidbase.core.data.repository.main.MainRepository
import com.papirus.androidbase.core.data.repository.main.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindsMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository
}
