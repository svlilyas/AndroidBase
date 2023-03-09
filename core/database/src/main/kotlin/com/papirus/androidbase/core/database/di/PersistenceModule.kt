package com.papirus.androidbase.core.database.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplates
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import com.papirus.androidbase.core.database.EncryptedDataStoreManager
import com.papirus.androidbase.core.database.db.AppDao
import com.papirus.androidbase.core.database.db.AppDatabase
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.DATASTORE_FILE
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.ENCRYPTION_TYPE
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.KEYSET_NAME
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.MASTER_KEY_URI
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.PREFERENCE_FILE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Singleton
    @Provides
    fun provideAead(application: Application): Aead {
        AeadConfig.register()

        return AndroidKeysetManager.Builder()
            .withSharedPref(application, KEYSET_NAME, PREFERENCE_FILE)
            .withKeyTemplate(KeyTemplates.get(ENCRYPTION_TYPE)).withMasterKeyUri(MASTER_KEY_URI)
            .build().keysetHandle.getPrimitive(Aead::class.java)
    }

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }),
            produceFile = { appContext.preferencesDataStoreFile(DATASTORE_FILE) })

    @Singleton
    @Provides
    fun provideEncryptedDataStoreManager(
        dataStore: DataStore<Preferences>
    ): EncryptedDataStoreManager =
        EncryptedDataStoreManager(dataStore = dataStore)

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "App.db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideAppDao(appDatabase: AppDatabase): AppDao {
        return appDatabase.appDao()
    }
}
