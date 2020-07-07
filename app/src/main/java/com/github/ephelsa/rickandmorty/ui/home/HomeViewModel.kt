package com.github.ephelsa.rickandmorty.ui.home

import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption
import com.github.ephelsa.rickandmorty.resourceoption.usecase.GetAllResourceOptionsUseCase
import com.github.ephelsa.rickandmorty.shared.data.NetworkResource
import com.github.ephelsa.rickandmorty.shared.infraestructure.viewmodel.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
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
                    is NetworkResource.Loading -> handleLoading(true)
                    is NetworkResource.Success -> {
                        handleLoading(false)
                        handleAllResourceOptionChange(it.resultData.results)
                    }
                    is NetworkResource.Error -> handleLoading(false)
                }
            }
        }
    }

    private fun handleAllResourceOptionChange(resourceOptions: List<ResourceOption>) {
        _uiChange.value = UIChange.AllResourceOptions(resourceOptions)
    }

    private fun handleLoading(isLoading: Boolean) {
        _uiChange.value = UIChange.Loading(isLoading)
    }

    sealed class UIChange {
        object Init : UIChange()
        data class Loading(val isLoading: Boolean) : UIChange()
        data class AllResourceOptions(val resourceOptions: List<ResourceOption>) : UIChange()
    }
}