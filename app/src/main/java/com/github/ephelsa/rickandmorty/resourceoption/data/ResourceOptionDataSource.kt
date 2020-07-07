package com.github.ephelsa.rickandmorty.resourceoption.data

import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption
import com.github.ephelsa.rickandmorty.shared.data.NetworkResource
import com.github.ephelsa.rickandmorty.shared.domain.WrappedResultsInResponse

interface ResourceOptionDataSource {
    suspend fun getAllResourceOptions(): NetworkResource<WrappedResultsInResponse<List<ResourceOption>>>
}