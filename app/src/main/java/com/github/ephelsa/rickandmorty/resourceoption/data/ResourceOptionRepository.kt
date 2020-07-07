package com.github.ephelsa.rickandmorty.resourceoption.data

class ResourceOptionRepository(
    private val resourceOptionDataSource: ResourceOptionDataSource
) {
    suspend fun getAllResourceOptions() = resourceOptionDataSource.getAllResourceOptions()
}