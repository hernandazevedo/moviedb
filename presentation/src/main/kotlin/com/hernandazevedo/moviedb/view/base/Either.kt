package com.hernandazevedo.moviedb.view.base

/**
 * Based on https://medium.com/@lupajz/you-either-love-it-or-you-havent-used-it-yet-a55f9b866dbe
 */
sealed class Either<out E, out V> {
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()
    object Completed : Either<Nothing, Nothing>()

    fun <V> value(value: V): Either<Nothing, V> = Either.Value(value)
    fun <E> error(value: E): Either<E, Nothing> = Either.Error(value)
    fun completed(): Either<Nothing, Nothing> = Either.Completed

    fun <V> either(action: () -> V): Either<Exception, V> =
            try { value(action()) } catch (e: Exception) { error(e) }
}

inline infix fun <E, V, V2> Either<E, V>
        .map(f: (V) -> V2): Either<E, V2> = when(this) {
    is Either.Completed -> this
    is Either.Error -> this
    is Either.Value -> Either.Value(f(this.value))
}

infix fun <E, V, V2> Either<E, (V) -> V2>
        .apply(f: Either<E, V>): Either<E, V2> = when(this) {
    is Either.Completed -> this
    is Either.Error -> this
    is Either.Value -> f.map(this.value)
}

inline infix fun <E, V, V2> Either<E, V>
        .flatMap(f: (V) -> Either<E, V2>): Either<E, V2> = when(this) {
    is Either.Error -> this
    is Either.Completed -> this
    is Either.Value -> f(value)
}

inline infix fun <E, E2, V> Either<E, V>
        .mapError(f: (E) -> E2): Either<E2, V> = when(this) {
    is Either.Completed -> this
    is Either.Error -> Either.Error(f.invoke(error))
    is Either.Value -> this
}

inline fun <E, V, A> Either<E, V>
        .fold(e: (E) -> A, v: (V) -> A): A = when(this) {
    is Either.Completed -> v(this.completed())
    is Either.Error -> e(this.error)
    is Either.Value -> v(this.value)
}