package com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter

import com.github.ephelsa.rickandmorty.shared.domain.WrappedResultsInResponse

fun <InType : Any, OutType : Any> List<InType>.changeListType(mapper: InType.() -> OutType): List<OutType> {
    val outTypeList = mutableListOf<OutType>()
    this.forEach { outTypeList.add(it.mapper()) }

    return outTypeList
}

fun <InType : Any, OutType : Any> WrappedResultsInResponse<InType>.changeResultType(mapper: InType.() -> OutType): WrappedResultsInResponse<OutType> {
    return WrappedResultsInResponse<OutType>(info = info, results = results.mapper())
}