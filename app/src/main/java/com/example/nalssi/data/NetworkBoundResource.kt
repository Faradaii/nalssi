package com.example.nalssi.data

import com.example.nalssi.core.utils.AppExecutors
import com.example.nalssi.data.datasources.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.Date

abstract class NetworkBoundResource<ResultType, RequestType> {
    val result: Flow<DataState<ResultType>> = flow {
        emit(DataState.Loading)
        val dbValue = loadFromDb().first()
        val lastUpdatedDate = getLastUpdatedDate()
        if (shouldFetch(dbValue, lastUpdatedDate)) {
            emit(DataState.Loading)
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDb().map { DataState.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(DataState.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Loading -> emit(DataState.Loading)
            }
        } else {
            emitAll(loadFromDb().map { DataState.Cached(it) })
        }
    }

    abstract fun loadFromDb(): Flow<ResultType>

    open fun onFetchFailed() {}

    abstract fun getLastUpdatedDate(): Date

    abstract fun shouldFetch(data: ResultType?, lastUpdatedDate: Date?): Boolean

    abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<DataState<ResultType>> = result
}