package com.example.jokeapp.domain.model

data class Jokes(
    val id: Int,
    val punchline: String,
    val setup: String,
    val type: String
)
