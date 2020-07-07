package com.github.ephelsa.rickandmorty.integrationtest

import com.github.ephelsa.rickandmorty.ScopedCoroutineTestAbstract
import com.github.ephelsa.rickandmorty.character.data.CharacterRepository
import com.github.ephelsa.rickandmorty.character.infrastructure.CharacterServiceDataSource
import com.github.ephelsa.rickandmorty.character.infrastructure.CharacterServiceRepository
import com.github.ephelsa.rickandmorty.character.usecase.GetCharactersUseCase
import com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient.RetrofitBuild
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
class CharacterTest : ScopedCoroutineTestAbstract() {

    private val retrofit: Retrofit = RetrofitBuild.retrofit
    private lateinit var characterServiceDataSource: CharacterServiceDataSource
    private lateinit var characterServiceRepository: CharacterServiceRepository
    private lateinit var characterRepository: CharacterRepository

    // Use cases
    private lateinit var getAllCharactersUseCase: GetCharactersUseCase

    @Before
    override fun setUp() {
        super.setUp()
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }

    override suspend fun setUpCoroutineProcess() {
        characterServiceDataSource = retrofit.create(CharacterServiceDataSource::class.java)
        characterServiceRepository = CharacterServiceRepository(characterServiceDataSource)
        characterRepository = CharacterRepository(characterServiceRepository)
    }

    override suspend fun tearDownCoroutineProcess() {
    }

    @Test
    fun `Use case GetCharactersUseCase`() {
        runBlocking(scopedCoroutineImpl.coroutineContext) {
            getAllCharactersUseCase = GetCharactersUseCase(characterRepository)
            val useCase = getAllCharactersUseCase()

            Assert.assertNotNull(useCase.data?.results)
        }
    }
}