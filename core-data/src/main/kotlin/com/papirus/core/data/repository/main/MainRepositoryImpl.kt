package com.papirus.core.data.repository.main

import androidx.annotation.WorkerThread
import com.papirus.core.data.client.main.MainClient
import com.papirus.core.data.mapper.ErrorResponseMapper
import com.papirus.core.database.db.AppDao
import com.papirus.core.model.remote.response.Note
import com.papirus.core.model.remote.response.SampleResponse
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

internal class MainRepositoryImpl @Inject constructor(
    private val mainClient: MainClient,
    private val appDao: AppDao,
    private val ioDispatcher: CoroutineDispatcher
) : MainRepository {

    @WorkerThread
    override fun fetchSampleInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<SampleResponse> = flow {
        val sampleInfo = appDao.getSampleInfo(name)
        if (sampleInfo == null) {
            val response = mainClient.fetchSampleInfo(name = name)
            response.suspendOnSuccess {
                appDao.insert(data)
                emit(data)
            }
                // handles the case when the API request gets an error response.
                // e.g., internal server error.
                .onError {
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                // handles the case when the API request gets an exception response.
                // e.g., network connection error.
                .onException { onError(message) }
        } else {
            emit(sampleInfo)
        }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    override fun insertNote(
        note: Note,
        onComplete: () -> Unit
    ): Flow<Unit> = flow {
        emit(appDao.insert(note = note))
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    override fun updateNote(
        note: Note,
        onComplete: () -> Unit
    ): Flow<Unit> = flow {
        emit(appDao.updateWithTimeStamp(note = note))
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    override fun deleteNote(
        note: Note,
        onComplete: () -> Unit
    ): Flow<Int> = flow {
        emit(appDao.delete(note = note))
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    override fun getAllNotes(
        onComplete: () -> Unit
    ): Flow<List<Note>> = flow {
        emit(appDao.getAllNotes())
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
