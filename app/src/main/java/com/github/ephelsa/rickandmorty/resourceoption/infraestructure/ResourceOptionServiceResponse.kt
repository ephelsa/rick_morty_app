package com.github.ephelsa.rickandmorty.resourceoption.infraestructure

import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption

data class ResourceOptionResultsJSON(
    val results: List<ResourceOptionJSON>
)

data class ResourceOptionJSON(
    val thumbnail_url: String,
    val title: String,
    val description: String
)

fun ResourceOptionJSON.buildDomain(): ResourceOption = ResourceOption(
    thumbnailURL = thumbnail_url,
    title = title,
    description = description
)
