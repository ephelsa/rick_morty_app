package com.github.ephelsa.rickandmorty.character.data

import com.github.ephelsa.rickandmorty.character.domain.Character
import com.github.ephelsa.rickandmorty.shared.data.NetworkResource
import com.github.ephelsa.rickandmorty.shared.domain.WrappedResultsInResponse

interface CharacterDataSource {
    suspend fun getCharacters(): NetworkResource<WrappedResultsInResponse<List<Character>>>
}