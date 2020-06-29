package com.github.ephelsa.rickandmorty.shared.infraestructure.scopecoroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job

class ScopedCoroutineImpl(override val uiDispatcher: CoroutineDispatcher) : ScopedCoroutine {
    override lateinit var job: Job
}