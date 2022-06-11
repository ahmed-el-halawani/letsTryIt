package com.newcore.letstryit.core.util.formvalidator2

interface IValidator {
    fun validate(value: String?): ValidatorResult
}