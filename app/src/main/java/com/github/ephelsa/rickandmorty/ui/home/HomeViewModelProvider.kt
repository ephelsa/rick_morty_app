package com.github.ephelsa.rickandmorty.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.ephelsa.rickandmorty.resourceoption.data.ResourceOptionRepository
import com.github.ephelsa.rickandmorty.resourceoption.infraestructure.ResourceOptionServiceRepository
import com.github.ephelsa.rickandmorty.resourceoption.usecase.GetAllResourceOptionsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class HomeViewModelProvider(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val repositoryServiceRepository = ResourceOptionServiceRepository(application.applicationContext)
        val resourceOptionRepository = ResourceOptionRepository(repositoryServiceRepository)
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