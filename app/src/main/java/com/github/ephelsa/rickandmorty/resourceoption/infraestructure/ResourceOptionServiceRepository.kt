package com.github.ephelsa.rickandmorty.resourceoption.infraestructure

import android.content.Context
import com.github.ephelsa.rickandmorty.R
import com.github.ephelsa.rickandmorty.resourceoption.data.ResourceOptionDataSource
import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption
import com.github.ephelsa.rickandmorty.shared.data.NetworkResource
import com.github.ephelsa.rickandmorty.shared.domain.WrappedResultsInResponse
import com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient.NetworkResourceHandler
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.MoshiBuild
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.changeListType
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.changeResultType
import com.github.ephelsa.rickandmorty.shared.util.RawResource
import com.squareup.moshi.Types
import kotlinx.coroutines.delay
import kotlin.random.Random

class ResourceOptionServiceRepository(private val context: Context) : ResourceOptionDataSource {

    @ExperimentalStdlibApi
    override suspend fun getAllResourceOptions(): NetworkResource<WrappedResultsInResponse<List<ResourceOption>>> {
        return NetworkResourceHandler.handleResponse<WrappedResultsInResponse<List<ResourceOption>>> {
            val json = RawResource.readRawJSON(context, R.raw.resource_option)
            val listType = Types.newParameterizedType(List::class.java, ResourceOptionJSON::class.java)
            val wrappedType = Types.newParameterizedType(WrappedResultsInResponse::class.java, listType)
            val wrappedJsonAdapter = MoshiBuild.moshi
                .adapter<WrappedResultsInResponse<List<ResourceOptionJSON>>>(wrappedType)

            val response = wrappedJsonAdapter.fromJson(json)

            delay(Random.nextLong(1000, 10000))

            response!!.changeResultType {
                changeListType {
                    buildDomain()
                }
            }
        }
    }
}