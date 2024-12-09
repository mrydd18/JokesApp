package com.example.jokeapp.data.remote.dto

data class JokesDto(
    val id: Int,
    val setup: String,
    val punchline: String,
    val type: String
)