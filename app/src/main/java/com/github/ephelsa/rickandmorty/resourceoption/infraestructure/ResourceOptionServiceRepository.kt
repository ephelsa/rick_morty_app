package com.github.ephelsa.rickandmorty.resourceoption.infraestructure

import android.content.Context
import com.github.ephelsa.rickandmorty.R
import com.github.ephelsa.rickandmorty.resourceoption.data.ResourceOptionDataSource
import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption
import com.github.ephelsa.rickandmorty.shared.data.NetworkResource
import com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient.NetworkResourceHandler
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.MoshiBuild
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.changeListType
import com.github.ephelsa.rickandmorty.shared.util.RawResource
import kotlinx.coroutines.delay
import kotlin.random.Random

class ResourceOptionServiceRepository(private val context: Context) : ResourceOptionDataSource {

    override suspend fun getAllResourceOptions(): NetworkResource<List<ResourceOption>> {
        return NetworkResourceHandler.handleResponse {
            val json = RawResource.readRawJSON(context, R.raw.resource_option)
            val jsonAdapter = MoshiBuild.moshi.adapter(ResourceOptionResultsJSON::class.java)
            val resourceOptionResultsJSON = jsonAdapter.fromJson(json)

            delay(Random.nextLong(1000, 10000))

            resourceOptionResultsJSON?.results?.changeListType { buildDomain() } ?: listOf()
        }
    }
}