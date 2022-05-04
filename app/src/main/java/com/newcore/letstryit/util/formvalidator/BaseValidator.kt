package com.newcore.letstryit.util.formvalidator

abstract class BaseValidator<T>(val value: T?) {
    var message: String? = null

    abstract fun checkValidation()

    fun isValid() = checkValidation().let { message == null }
}