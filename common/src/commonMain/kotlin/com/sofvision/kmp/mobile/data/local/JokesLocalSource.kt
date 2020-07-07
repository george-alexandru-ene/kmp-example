package com.sofvision.kmp.mobile.data.local

import com.softvision.kmp.JokeDb
import com.softvision.kmp.JokesDatabase
import com.sofvision.kmp.mobile.domain.entities.Joke

class JokesLocalSource(private val db: JokesDatabase) {
    fun getJokes(): List<Joke> {
        return db.jokeQueries.getAllJokes().executeAsList().map {
            Joke(
                id = it.id,
                setup = it.setup,
                punchline = it.punchline,
                type = it.type
            )
        }
    }

    fun saveJokes(jokes: List<Joke>) {
        jokes.forEach {
            db.jokeQueries.insertJoke(
                JokeDb.Impl(
                    id = it.id,
                    setup = it.setup,
                    punchline = it.punchline,
                    type = it.type
                )
            )
        }
    }
}