package io.github.cbinarycastle.kurly.domain

import io.github.cbinarycastle.kurly.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class UseCase<P, R>(private val dispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(params: P): Result<R> = try {
        withContext(dispatcher) {
            execute(params).let {
                Result.Success(it)
            }
        }
    } catch (e: Exception) {
        Timber.e(e)
        Result.Error(e)
    }

    abstract fun execute(params: P): R
}