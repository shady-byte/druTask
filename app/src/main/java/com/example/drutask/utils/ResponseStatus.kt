package com.example.drutask.utils

sealed class ResponseStatus<out T> {
    data class OnSuccess<out T>(val data: T) : ResponseStatus<T>()
    data class OnError(val exception: Throwable) : ResponseStatus<Nothing>()
    object IsLoading : ResponseStatus<Nothing>()
}
