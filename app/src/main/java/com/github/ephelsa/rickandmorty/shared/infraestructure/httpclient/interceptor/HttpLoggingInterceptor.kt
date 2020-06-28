package com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient.interceptor

import okhttp3.logging.HttpLoggingInterceptor

object HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}