package com.github.ephelsa.rickandmorty.shared.domain

data class WrappedResultsInResponse<ResultType : Any>(
    val info: Info,
    val results: ResultType
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
