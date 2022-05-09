package com.newcore.letstryit.util

sealed class Either<F, D> {
    data class Failure<F, D>(val failure: F, val message: String? = "") : Either<F, D>()
    data class Success<F, D>(val done: D?, val message: String? = "") : Either<F, D>()
}