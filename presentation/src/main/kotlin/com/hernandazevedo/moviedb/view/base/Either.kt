package com.hernandazevedo.moviedb.view.base

/**
 * Based on https://medium.com/@lupajz/you-either-love-it-or-you-havent-used-it-yet-a55f9b866dbe
 */
sealed class Either<out E, out V> {
    data class Left<out E>(val error: E) : Either<E, Nothing>()
    data class Right<out V>(val value: V) : Either<Nothing, V>()

    fun <V> right(value: V): Either<Nothing, V> = Either.Right(value)
    fun <E> left(value: E): Either<E, Nothing> = Either.Left(value)

    fun <V> either(action: () -> V): Either<Exception, V> =
            try { right(action()) } catch (e: Exception) { left(e) }
}

inline infix fun <E, V, V2> Either<E, V>
        .map(f: (V) -> V2): Either<E, V2> = when (this) {
    is Either.Left -> this
    is Either.Right -> Either.Right(f(this.value))
}

infix fun <E, V, V2> Either<E, (V) -> V2>
        .apply(f: Either<E, V>): Either<E, V2> = when (this) {
    is Either.Left -> this
    is Either.Right -> f.map(this.value)
}

inline infix fun <E, V, V2> Either<E, V>
        .flatMap(f: (V) -> Either<E, V2>): Either<E, V2> = when (this) {
    is Either.Left -> this
    is Either.Right -> f(value)
}

inline infix fun <E, E2, V> Either<E, V>
        .mapError(f: (E) -> E2): Either<E2, V> = when (this) {
    is Either.Left -> Either.Left(f.invoke(error))
    is Either.Right -> this
}

inline fun <E, V, A> Either<E, V>
        .fold(e: (E) -> A, v: (V) -> A): A = when (this) {
    is Either.Left -> e(this.error)
    is Either.Right -> v(this.value)
}