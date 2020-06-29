package com.github.ephelsa.rickandmorty.character.infrastructure

import com.github.ephelsa.rickandmorty.shared.domain.WrappedResultsInResponse
import retrofit2.http.GET

interface CharacterServiceDataSource {
    @GET("character/")
    suspend fun getAllCharacters(): WrappedResultsInResponse<List<CharacterJSON>>
}