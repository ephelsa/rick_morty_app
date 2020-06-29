package com.github.ephelsa.rickandmorty.character.domain

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    // TODO: Origin
    // TODO: Location
    val image: String,
    val episodes: List<String>,
    val url: String,
    val created: String
)
