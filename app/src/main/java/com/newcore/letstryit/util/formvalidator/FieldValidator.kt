package com.newcore.letstryit.util.formvalidator

abstract class FieldValidator<T>(val value: T?) {
    var message: String? = null

    /**
     * call checkValidation() method at "init" function of the child class
     * to check field validation every time users enters value
     */
    abstract fun checkValidation()

    abstract fun reset(): FieldValidator<T>

    fun isValid() = checkValidation().let { message == null }
}