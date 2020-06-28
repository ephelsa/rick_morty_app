package com.github.ephelsa.rickandmorty.shared.data

sealed class NetworkResource<out DataType : Any>(
    val data: DataType? = null,
    val error: Exception? = null,
    val message: String? = null
) {
    data class Loading(val waitingMessage: String) :
        NetworkResource<Nothing>(message = waitingMessage)

    data class Success<DataType : Any>(val results: DataType) :
        NetworkResource<DataType>(data = results)

    data class Error(val errorMessage: String, val exception: Exception) :
        NetworkResource<Nothing>(message = errorMessage, error = exception)
}