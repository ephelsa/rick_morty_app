package com.github.ephelsa.rickandmorty.character.data

class CharacterRepository(
    private val characterDataSource: CharacterDataSource
) {
    suspend fun getCharacters() = characterDataSource.getCharacters()
}