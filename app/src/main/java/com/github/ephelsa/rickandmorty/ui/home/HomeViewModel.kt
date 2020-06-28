package com.github.ephelsa.rickandmorty.ui.home

import android.util.Log
import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption
import com.github.ephelsa.rickandmorty.resourceoption.usecase.GetAllResourceOptionsUseCase
import com.github.ephelsa.rickandmorty.shared.data.NetworkResource
import com.github.ephelsa.rickandmorty.shared.infraestructure.viewmodel.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel(
        uiDispatcher: CoroutineDispatcher,
        private val getAllResourceOptionsUseCase: GetAllResourceOptionsUseCase
) : ScopedViewModel(uiDispatcher) {

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

    private val _uiChange = MutableStateFlow<UIChange>(UIChange.Init)
    val uiChange: StateFlow<UIChange>
        get() = _uiChange


    init {
        getAllResourceOptions()
    }

    private fun getAllResourceOptions() {
        launch {
            flow {
                emit(NetworkResource.Loading("Loading"))
                emit(getAllResourceOptionsUseCase())
            }.collect {
                when (it) {
                    is NetworkResource.Loading -> Log.v(TAG, "On Loading -> ${it.message}")
                    is NetworkResource.Success -> handleAllResourceOptionChange(it.data!!)
                    is NetworkResource.Error -> Log.v(TAG, "On Error -> ${it.errorMessage}")
                }
            }
        }
    }

    private fun handleAllResourceOptionChange(resourceOptions: List<ResourceOption>) {
        _uiChange.value = UIChange.AllResourceOptions(resourceOptions)
    }

    sealed class UIChange {
        object Init : UIChange()
        data class AllResourceOptions(val resourceOptions: List<ResourceOption>) : UIChange()
    }
}