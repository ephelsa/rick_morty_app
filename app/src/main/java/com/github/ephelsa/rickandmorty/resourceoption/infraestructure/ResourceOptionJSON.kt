package com.github.ephelsa.rickandmorty.resourceoption.infraestructure

import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.JSON

data class ResourceOptionJSON(
    val thumbnail_url: String,
    val title: String,
    val description: String
) : JSON<ResourceOption>() {

    override fun buildDomain(): ResourceOption = ResourceOption(
        thumbnailURL = thumbnail_url,
        title = title,
        description = description
    )

}
