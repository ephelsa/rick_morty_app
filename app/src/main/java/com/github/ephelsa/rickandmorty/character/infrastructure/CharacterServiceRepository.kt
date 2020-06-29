package com.github.ephelsa.rickandmorty.character.infrastructure

import com.github.ephelsa.rickandmorty.character.data.CharacterDataSource
import com.github.ephelsa.rickandmorty.character.domain.Character
import com.github.ephelsa.rickandmorty.shared.data.NetworkResource
import com.github.ephelsa.rickandmorty.shared.domain.WrappedResultsInResponse
import com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient.NetworkResourceHandler
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.changeListType
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.changeResultType

class CharacterServiceRepository(
    private val characterServiceDataSource: CharacterServiceDataSource
) : CharacterDataSource {

    override suspend fun getCharacters(): NetworkResource<WrappedResultsInResponse<List<Character>>> {
        return NetworkResourceHandler.handleResponse {
            characterServiceDataSource.getAllCharacters()
                .changeResultType {
                    changeListType {
                        buildDomain()
                    }
                }
        }
    }
}