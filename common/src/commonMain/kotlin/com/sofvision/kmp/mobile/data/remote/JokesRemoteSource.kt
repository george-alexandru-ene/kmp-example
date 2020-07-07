package com.sofvision.kmp.mobile.data.remote

import com.sofvision.kmp.mobile.domain.entities.Joke
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlin.reflect.typeOf

@OptIn(ExperimentalStdlibApi::class)
class JokesRemoteSource(clientEngine: HttpClientEngine) {
    private val client = HttpClient(clientEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                typeOf<List<Joke>>()
            }
        }
    }

    suspend fun getJokes(): List<Joke> =
        client.get("https://official-joke-api.appspot.com/jokes/ten")
}