package com.example.jokeapp.presentation.screens.jokeGenerator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapp.core.OperationStatus
import com.example.jokeapp.data.repository.JokesRepositoryImpl
import com.example.jokeapp.domain.model.Jokes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class JokesGeneratorViewModel : ViewModel() {
    private val jokesRepository = JokesRepositoryImpl()

    val jokesFlow = MutableStateFlow<Jokes?>(null)
    private val showError = MutableSharedFlow<Exception>()

    fun getRandomJoke() = viewModelScope.launch {
        val status = jokesRepository.getRandomJoke()
        when (status) {
            is OperationStatus.Success -> {
                jokesFlow.emit(status.value)
            }

            is OperationStatus.Failure -> {
                showError.emit(status.exception)
            }
        }
    }

    fun saveRandomJoke() = viewModelScope.launch {
        val value = jokesFlow.value
        if (value != null) {
            jokesRepository.saveJoke(value)
        }
    }

}