package com.github.ephelsa.rickandmorty.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.ephelsa.rickandmorty.resourceoption.data.ResourceOptionRepository
import com.github.ephelsa.rickandmorty.resourceoption.infraestructure.ResourceOptionRepositoryDataSource
import com.github.ephelsa.rickandmorty.resourceoption.usecase.GetAllResourceOptionsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class HomeViewModelProvider : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val repositoryDataSource = ResourceOptionRepositoryDataSource()
        val resourceOptionRepository = ResourceOptionRepository(repositoryDataSource)
        val getAllResourceOptionsUseCase = GetAllResourceOptionsUseCase(resourceOptionRepository)

        return modelClass
            .getConstructor(
                CoroutineDispatcher::class.java,
                GetAllResourceOptionsUseCase::class.java
            )
            .newInstance(
                Dispatchers.IO,
                getAllResourceOptionsUseCase
            )
    }

}