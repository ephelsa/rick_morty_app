package com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient

import com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient.interceptor.HttpLoggingInterceptor.httpLoggingInterceptor
import okhttp3.OkHttpClient

object OkHttpClientBuild {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
}