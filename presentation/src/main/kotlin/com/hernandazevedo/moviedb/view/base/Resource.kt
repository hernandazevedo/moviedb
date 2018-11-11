package com.hernandazevedo.moviedb.view.base

class Resource<out T>(val status: Status,
                      val data: T?,
                      val throwable: Throwable?) {
    companion object {
        const val HASHCODE_MULTIPLIER = 31
        const val ZERO = 0
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(Status.ERROR, null, throwable)
        }

        fun <T> error(data: T, throwable: Throwable?): Resource<T> {
            return Resource(Status.ERROR, data, throwable)
        }
    }

    override fun equals(other: Any?): Boolean {

        other as Resource<*>
        return if (this === other) {
            true
        } else !(javaClass != other.javaClass ||
                status != other.status ||
                data != other.data ||
                throwable != other.throwable)
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = HASHCODE_MULTIPLIER * result + (data?.hashCode() ?: ZERO)
        result = HASHCODE_MULTIPLIER * result + (throwable?.hashCode() ?: ZERO)
        return result
    }
}

enum class Status {
    SUCCESS,
    ERROR
}