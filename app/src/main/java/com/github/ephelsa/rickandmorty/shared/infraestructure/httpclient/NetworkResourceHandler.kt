package com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient

import com.github.ephelsa.rickandmorty.shared.data.NetworkResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object NetworkResourceHandler {
    private fun <DataType : Any> handleSuccess(data: DataType): NetworkResource.Success<DataType> {
        return NetworkResource.Success(data)
    }

    private fun handleError(exception: Exception): NetworkResource.Error {
        // TODO: Implementation

        return NetworkResource.Error("Error, holy shit", exception)
    }

    suspend fun <DataType : Any> handleResponse(
        response: suspend () -> DataType
    ): NetworkResource<DataType> {
        return withContext(Dispatchers.IO) {
            try {
                handleSuccess(response())
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }
}