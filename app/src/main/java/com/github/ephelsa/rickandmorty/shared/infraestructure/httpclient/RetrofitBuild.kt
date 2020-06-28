package com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient

import com.github.ephelsa.rickandmorty.BuildConfig
import com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient.OkHttpClientBuild.okHttpClient
import com.github.ephelsa.rickandmorty.shared.infraestructure.jsonconverter.MoshiBuild
import retrofit2.Retrofit


object RetrofitBuild {
    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiBuild.moshiConverterFactory)
        .baseUrl(BuildConfig.BASE_URL_RICK_MORTY)
        .client(okHttpClient)
        .build()
}