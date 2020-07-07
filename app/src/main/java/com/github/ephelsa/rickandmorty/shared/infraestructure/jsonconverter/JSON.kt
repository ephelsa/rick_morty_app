package com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter

abstract class JSON<DomainClass : Any> {
    abstract fun buildDomain(): DomainClass
}