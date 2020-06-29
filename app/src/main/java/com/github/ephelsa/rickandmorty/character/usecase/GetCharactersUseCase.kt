package com.github.ephelsa.rickandmorty.character.usecase

import com.github.ephelsa.rickandmorty.character.data.CharacterRepository

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke() = characterRepository.getCharacters()
}