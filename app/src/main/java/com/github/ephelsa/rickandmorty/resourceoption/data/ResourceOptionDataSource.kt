package com.github.ephelsa.rickandmorty.resourceoption.data

import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption
import com.github.ephelsa.rickandmorty.shared.data.NetworkResource

interface ResourceOptionDataSource {
    suspend fun getAllResourceOptions(): NetworkResource<List<ResourceOption>>
}