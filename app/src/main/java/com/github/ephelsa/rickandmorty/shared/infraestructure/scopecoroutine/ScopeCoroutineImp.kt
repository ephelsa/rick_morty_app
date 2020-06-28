package com.github.ephelsa.rickandmorty.shared.infraestructure.scopecoroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job

class ScopeCoroutineImp(override val uiDispatcher: CoroutineDispatcher) : ScopeCoroutine {
    override lateinit var job: Job
}