package com.github.ephelsa.rickandmorty.resourceoption.domain

data class ResourceOption(
    val thumbnailURL: String,
    val title: String,
    val description: String = ""
) {
    override fun toString(): String {
        return title
    }
}