package com.example.jokeapp.data

import com.example.jokeapp.data.local.entity.JokeDbo
import com.example.jokeapp.data.remote.dto.JokesDto
import com.example.jokeapp.domain.model.Jokes

fun JokesDto.toJokes() = Jokes(
    id = id,
    punchline = punchline,
    setup = setup,
    type = type
)

fun Jokes.toJokeDbo() = JokeDbo(
    id, punchline, setup
)

fun JokeDbo.toJokes() = Jokes(
    id = id,
    punchline = punchline,
    setup = setup,
    type = "default"
)