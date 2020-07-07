package com.github.ephelsa.rickandmorty

import com.github.ephelsa.rickandmorty.shared.infraestructure.scopecoroutine.ScopedCoroutineImpl
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
abstract class ScopedCoroutineAndroidTestAbstract {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    protected lateinit var scopedCoroutineImpl: ScopedCoroutineImpl

    open fun setUp() {
        configureSetUp()
        runBlocking(scopedCoroutineImpl.coroutineContext) { setUpCoroutineProcess() }
    }

    open fun tearDown() {
        runBlocking(scopedCoroutineImpl.coroutineContext) { tearDownCoroutineProcess() }
        configureTearDown()
    }

    abstract suspend fun setUpCoroutineProcess()
    abstract suspend fun tearDownCoroutineProcess()

    private fun configureSetUp() {
        scopedCoroutineImpl = ScopedCoroutineImpl(testDispatcher)
        scopedCoroutineImpl.createScope()
        Dispatchers.setMain(testDispatcher)
    }

    private fun configureTearDown() {
        Dispatchers.resetMain()
        scopedCoroutineImpl.destroyScope()
        testDispatcher.cleanupTestCoroutines()
    }
}