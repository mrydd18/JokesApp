package com.example.jokeapp.data.repository

import com.example.jokeapp.JokesApplication
import com.example.jokeapp.core.NetworkCallHelper
import com.example.jokeapp.core.OperationStatus
import com.example.jokeapp.core.map
import com.example.jokeapp.data.local.JokeDatabase
import com.example.jokeapp.data.remote.RetrofitInstance
import com.example.jokeapp.data.remote.dto.JokesDto
import com.example.jokeapp.data.toJokeDbo
import com.example.jokeapp.data.toJokes
import com.example.jokeapp.domain.model.Jokes
import com.example.jokeapp.domain.repository.JokesRepository

class JokesRepositoryImpl : JokesRepository {

    private val jokesService = RetrofitInstance.jokesService
    private val jokeDao = JokeDatabase.create(JokesApplication.context).jokeDao

    override suspend fun getRandomJoke(): OperationStatus<Jokes> {
        return NetworkCallHelper.safeApiCall<JokesDto> {
            jokesService.getRandomJoke()
        }.map {jokesDto -> jokesDto.toJokes()}
    }

    override suspend fun saveJoke(joke: Jokes): OperationStatus<Unit> {
        return NetworkCallHelper.safeRoomCall {
            jokeDao.saveJoke(joke.toJokeDbo())
        }
    }

    override suspend fun getAllSavedJokes(): OperationStatus<List<Jokes>> {
        return NetworkCallHelper.safeRoomCall {
            jokeDao.getAllJokes().map { it.toJokes() }
        }
    }

    override suspend fun deleteJoke(joke: Jokes): OperationStatus<Unit> {
        return NetworkCallHelper.safeRoomCall {
            jokeDao.deleteJoke(joke.toJokeDbo())
        }
    }
}