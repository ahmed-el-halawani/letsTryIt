package com.newcore.myformvalidation

import com.newcore.myformvalidation.validators.EmptyValidator


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

