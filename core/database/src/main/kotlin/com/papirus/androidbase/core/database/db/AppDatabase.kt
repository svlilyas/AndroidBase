package com.papirus.androidbase.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.papirus.androidbase.core.model.remote.response.Note
import com.papirus.androidbase.core.model.remote.response.SampleResponse

@Database(entities = [SampleResponse::class, Note::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}
