package com.github.ephelsa.rickandmorty.shared.util

import android.content.Context

object RawResource {
    fun readRawJSON(context: Context, rawResource: Int): String = context.resources
        .openRawResource(rawResource)
        .bufferedReader()
        .use { it.readText() }
}