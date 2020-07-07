package com.github.ephelsa.rickandmorty

import com.github.ephelsa.rickandmorty.shared.domain.Info
import com.github.ephelsa.rickandmorty.shared.domain.WrappedResultsInResponse
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.changeListType
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.changeResultType
import org.junit.Assert
import org.junit.Test

class ListTransformerTest {

    @Test
    fun `Transformation from a Infrastructure list to a Domain list`() {
        val intList = listOf(1, 2, 3, 4)
        val results = intList.changeListType { toString() }

        Assert.assertEquals(results[0], intList[0].toString())
    }

    @Test
    fun `Transformation of WrappedResultsInResponse from Infrastructure to Domain`() {
        val info = Info(0, 0, null, null)

        val intResults = WrappedResultsInResponse<Int>(info, 20)
        val transformation = intResults.changeResultType { toString() }

        Assert.assertEquals(transformation.results, intResults.results.toString())
    }
}