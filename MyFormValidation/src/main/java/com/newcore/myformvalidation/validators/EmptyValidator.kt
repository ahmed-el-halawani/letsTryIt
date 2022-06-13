package com.newcore.myformvalidation.validators

import com.newcore.myformvalidation.IValidator
import com.newcore.myformvalidation.ValidatorResult
import com.newcore.myformvalidation.ValidatorResultCodes
import com.newcore.myformvalidation.ValidatorsBuild

class EmptyValidator(
    var customMessage: String? = null,
) : IValidator {

    override fun validate(value: String?): ValidatorResult {
        if (value.isNullOrBlank())
            return ValidatorResult.Failure(
                ValidatorResultCodes.EmptyField,
                customMessage ?: "must not be empty field"
            )

        return ValidatorResult.Success()
    }
}

fun ValidatorsBuild.emptyValidator(customMessage: String? = null) {
    (validatorsList.first { it is EmptyValidator } as EmptyValidator).customMessage = customMessage
}