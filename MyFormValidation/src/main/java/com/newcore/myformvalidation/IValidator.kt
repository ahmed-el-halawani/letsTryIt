package com.newcore.myformvalidation

interface IValidator {
    fun validate(value: String?): ValidatorResult
}