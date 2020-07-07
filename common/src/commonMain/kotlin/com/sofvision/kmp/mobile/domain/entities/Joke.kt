package com.sofvision.kmp.mobile.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Joke(
    val id: Long,
    val type: String,
    val setup: String,
    val punchline: String
) {
    var isPunchlineVisible = false
}