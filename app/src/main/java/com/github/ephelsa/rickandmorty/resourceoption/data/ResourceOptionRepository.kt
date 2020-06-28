package com.github.ephelsa.rickandmorty.resourceoption.data

import com.github.ephelsa.rickandmorty.shared.data.NetworkResource
import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption

class ResourceOptionRepository(
    private val resourceOptionDataSource: ResourceOptionDataSource
) {
    suspend fun getAllResourceOptions(): NetworkResource<List<ResourceOption>> =
        resourceOptionDataSource.getAllResourceOptions()
}