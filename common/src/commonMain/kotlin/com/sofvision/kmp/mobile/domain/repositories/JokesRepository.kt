package com.sofvision.kmp.mobile.domain.repositories

import com.sofvision.kmp.mobile.domain.entities.Joke

interface JokesRepository {
    suspend fun getJokes(): List<Joke>
}