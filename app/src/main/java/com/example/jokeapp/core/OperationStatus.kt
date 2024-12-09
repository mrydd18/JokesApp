package com.example.jokeapp.core

import com.example.jokeapp.data.remote.dto.JokesDto
import java.lang.Exception

sealed interface OperationStatus<SuccessType> {
    data class Success<SuccessType>(val value: SuccessType) : OperationStatus<SuccessType>
    data class Failure<SuccessType>(val exception: Exception) : OperationStatus<SuccessType>
}

fun <FromType, ToType> OperationStatus<FromType>.map(
    mapper: (FromType) -> ToType
): OperationStatus<ToType> {
    return when (this) {
        is OperationStatus.Success -> OperationStatus.Success(mapper(value))
        is OperationStatus.Failure -> OperationStatus.Failure(exception)
    }
}
