package com.github.ephelsa.rickandmorty.shared.infraestructure.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.github.ephelsa.rickandmorty.shared.infraestructure.scopecoroutine.ScopedCoroutine
import com.github.ephelsa.rickandmorty.shared.infraestructure.scopecoroutine.ScopedCoroutineImpl
import kotlinx.coroutines.CoroutineDispatcher

open class ScopedViewModel(uiDispatcher: CoroutineDispatcher) : ViewModel(),
    ScopedCoroutine by ScopedCoroutineImpl(uiDispatcher) {

    init {
        createScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}
