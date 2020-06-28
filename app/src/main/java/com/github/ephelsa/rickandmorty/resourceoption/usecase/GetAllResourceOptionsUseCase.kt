package com.github.ephelsa.rickandmorty.resourceoption.usecase

import com.github.ephelsa.rickandmorty.resourceoption.data.ResourceOptionRepository

class GetAllResourceOptionsUseCase(
    private val resourceOptionRepository: ResourceOptionRepository
) {
    suspend operator fun invoke() = resourceOptionRepository.getAllResourceOptions()
}