package com.papirus.androidbase.core.data.repository.main

import androidx.annotation.WorkerThread
import com.papirus.androidbase.core.model.remote.response.Note
import com.papirus.androidbase.core.model.remote.response.SampleResponse
import kotlinx.coroutines.flow.Flow

/** Repository is an interface for configuring base repository classes. */
interface MainRepository{
    @WorkerThread
    fun fetchSampleInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<SampleResponse>

    @WorkerThread
    fun insertNote(
        note: Note,
        onComplete: () -> Unit
    ): Flow<Unit>

    @WorkerThread
    fun updateNote(
        note: Note,
        onComplete: () -> Unit
    ): Flow<Unit>

    @WorkerThread
    fun deleteNote(
        note: Note,
        onComplete: () -> Unit
    ): Flow<Int>

    @WorkerThread
    fun getAllNotes(
        onComplete: () -> Unit
    ):Flow<List<Note>>
}
