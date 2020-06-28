package com.github.ephelsa.rickandmorty.shared.infraestructure.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.github.ephelsa.rickandmorty.shared.infraestructure.scopecoroutine.ScopeCoroutine
import com.github.ephelsa.rickandmorty.shared.infraestructure.scopecoroutine.ScopeCoroutineImp
import kotlinx.coroutines.CoroutineDispatcher

open class ScopedViewModel(uiDispatcher: CoroutineDispatcher) : ViewModel(),
    ScopeCoroutine by ScopeCoroutineImp(uiDispatcher) {

    init { createScope() }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}
