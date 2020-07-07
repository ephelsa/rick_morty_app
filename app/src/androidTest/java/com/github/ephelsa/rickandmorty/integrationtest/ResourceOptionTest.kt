package com.github.ephelsa.rickandmorty.integrationtest

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.ephelsa.rickandmorty.ScopedCoroutineAndroidTestAbstract
import com.github.ephelsa.rickandmorty.resourceoption.data.ResourceOptionRepository
import com.github.ephelsa.rickandmorty.resourceoption.infraestructure.ResourceOptionServiceRepository
import com.github.ephelsa.rickandmorty.resourceoption.usecase.GetAllResourceOptionsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ResourceOptionTest : ScopedCoroutineAndroidTestAbstract() {

    private lateinit var context: Context
    private lateinit var resourceOptionRepository: ResourceOptionRepository
    private lateinit var resourceOptionServiceRepository: ResourceOptionServiceRepository

    // Use case
    private lateinit var getAllResourceOptionsUseCase: GetAllResourceOptionsUseCase

    @Before
    override fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        super.setUp()
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }

    override suspend fun setUpCoroutineProcess() {
        resourceOptionServiceRepository = ResourceOptionServiceRepository(context)
        resourceOptionRepository = ResourceOptionRepository(resourceOptionServiceRepository)
    }

    override suspend fun tearDownCoroutineProcess() {
    }

    @Test
    fun test_GetAllResourceOptionsUseCase() {
        runBlocking(scopedCoroutineImpl.coroutineContext) {
            getAllResourceOptionsUseCase = GetAllResourceOptionsUseCase(resourceOptionRepository)
            val userCase = getAllResourceOptionsUseCase()

            Assert.assertNotNull(userCase.data?.results)
        }
    }

}