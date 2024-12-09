package com.example.jokeapp.presentation.screens.savedJokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapp.core.OperationStatus
import com.example.jokeapp.data.repository.JokesRepositoryImpl
import com.example.jokeapp.domain.model.Jokes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SavedJokesViewModel : ViewModel() {
    private val jokesRepository = JokesRepositoryImpl()

    private val _savedJokesFlow = MutableStateFlow<List<Jokes>>(emptyList())
    val savedJokesFlow: StateFlow<List<Jokes>> = _savedJokesFlow
    private val showError = MutableSharedFlow<Exception>()

    init {
        getAllSavedJokes()
    }

    fun getAllSavedJokes() = viewModelScope.launch {
        val status = jokesRepository.getAllSavedJokes() // Add this method to your repository
        when (status) {
            is OperationStatus.Success -> {
                _savedJokesFlow.emit(status.value)
            }

            is OperationStatus.Failure -> {
                showError.emit(status.exception)
            }
        }
    }

    fun deleteJoke(joke: Jokes) = viewModelScope.launch {
        val status = jokesRepository.deleteJoke(joke)
        when (status) {
            is OperationStatus.Success -> {
                getAllSavedJokes()
            }

            is OperationStatus.Failure -> {
                showError.emit(status.exception)
            }
        }
    }

}