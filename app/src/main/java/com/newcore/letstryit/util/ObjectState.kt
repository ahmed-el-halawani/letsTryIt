package com.newcore.letstryit.util

sealed class ObjectState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ObjectState<T>(data)
    class Error<T>(message: String, data: T? = null) : ObjectState<T>(data, message)
    class Loading<T>(data: T? = null) : ObjectState<T>(data)
}