package com.sofvision.kmp.mobile.domain.usecases

import com.sofvision.kmp.mobile.domain.repositories.JokesRepository

class GetJokes(private val jokesRepository: JokesRepository) {
    suspend operator fun invoke() = jokesRepository.getJokes()
}