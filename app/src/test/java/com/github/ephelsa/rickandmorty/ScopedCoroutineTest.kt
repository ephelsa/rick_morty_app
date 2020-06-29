package com.github.ephelsa.rickandmorty

import com.github.ephelsa.rickandmorty.shared.infraestructure.scopecoroutine.ScopedCoroutineImpl
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ScopedCoroutineTest {
    companion object {
        private const val SUPER_DELAY_RESPONSE = "Delay!"
    }

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    private lateinit var scopedCoroutineImpl: ScopedCoroutineImpl

    @Before
    fun setup() {
        scopedCoroutineImpl = ScopedCoroutineImpl(testDispatcher)
        scopedCoroutineImpl.createScope()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        scopedCoroutineImpl.destroyScope()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `scope started and superDelay function must return SUPER_DELAY_RESPONSE`() {
        runBlockingTest(scopedCoroutineImpl.coroutineContext) {
            val result = superDelay()

            Assert.assertNotNull("Job didn't start", result)
        }
    }

    @Test
    fun `scope cancelled and superDelay function must not work`() {
        var result = false
        var superDelayResult = ""

        try {
            scopedCoroutineImpl.destroyScope()

            runBlockingTest(scopedCoroutineImpl.coroutineContext) {
                superDelayResult = superDelay()
            }
        } catch (e: Exception) {
            result = true
        }

        Assert.assertTrue("Job didn't stop! superDelay function value is $superDelayResult", result)
    }

    private suspend fun superDelay(): String {
        delay(10000)

        return SUPER_DELAY_RESPONSE
    }
}