package com.example.drutask.domain.usecase

import com.example.drutask.utils.ResponseStatus


abstract class BaseUseCase<P,R> {
    suspend operator fun invoke(parameters: P): ResponseStatus<R> {
        return try {
            execute(parameters)
        } catch (ex: Exception) {
            ResponseStatus.OnError(ex)
        }
    }

    protected abstract suspend fun execute(parameters: P): ResponseStatus<R>
}