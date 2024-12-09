package com.example.jokeapp.domain.repository

import com.example.jokeapp.core.OperationStatus
import com.example.jokeapp.domain.model.Jokes

interface JokesRepository {

    suspend fun getRandomJoke(): OperationStatus<Jokes>

    suspend fun saveJoke(joke: Jokes): OperationStatus<Unit>

    suspend fun deleteJoke(joke: Jokes): OperationStatus<Unit>

    suspend fun getAllSavedJokes(): OperationStatus<List<Jokes>>
}