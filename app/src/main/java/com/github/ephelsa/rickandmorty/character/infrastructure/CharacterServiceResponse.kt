package com.github.ephelsa.rickandmorty.character.infrastructure

import com.github.ephelsa.rickandmorty.character.domain.Character

data class CharacterJSON(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

fun CharacterJSON.buildDomain(): Character = Character(
    id, name, status, species, type, gender, image, episode, url, created
)
