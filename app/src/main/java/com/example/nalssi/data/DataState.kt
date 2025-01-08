package com.example.nalssi.data

sealed class DataState<out T> {
    data class Success<out T>(val data: T): DataState<T>()
    data class Cached<out T>(val data: T): DataState<T>()
    data class Error(val errorMessage: String): DataState<Nothing>()
    data object Loading: DataState<Nothing>()
}