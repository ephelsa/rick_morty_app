package com.github.ephelsa.rickandmorty

import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.changeListType
import org.junit.Assert
import org.junit.Test

class ListTransformerTest {

    @Test
    fun `correct transformation from a infrastructure list to a domain list`() {
        val intList = listOf(1, 2, 3, 4)
        val results = intList.changeListType { toString() }

        Assert.assertEquals(results[0], intList[0].toString())
    }
}