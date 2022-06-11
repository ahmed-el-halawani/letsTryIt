package com.newcore.letstryit.core.util.formvalidator2

import com.newcore.letstryit.core.util.formvalidator2.validators.EmptyValidator

interface IValidator {
    fun validate(value: String?): ValidatorResult


}

data class ValidatorsBuild(
    val validatorsList: MutableList<IValidator> = mutableListOf(EmptyValidator()),
) {

    fun getValidationResult(value: String?): ValidatorResult {
        for (i in validatorsList) {
            val result = i.validate(value)
            if (result is ValidatorResult.Failure) {
                return result
            }
        }
        return ValidatorResult.Success()
    }

}

