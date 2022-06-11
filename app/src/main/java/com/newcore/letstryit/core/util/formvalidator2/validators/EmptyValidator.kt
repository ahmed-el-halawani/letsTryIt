package com.newcore.letstryit.core.util.formvalidator2.validators

import com.newcore.letstryit.core.util.formvalidator2.IValidator
import com.newcore.letstryit.core.util.formvalidator2.ValidatorResult
import com.newcore.letstryit.core.util.formvalidator2.ValidatorResultCodes
import com.newcore.letstryit.core.util.formvalidator2.ValidatorsBuild

class EmptyValidator(
    var customMessage: String? = null,
) : IValidator {

    override fun validate(value: String?): ValidatorResult {
        if (value.isNullOrBlank())
            return ValidatorResult.Failure(ValidatorResultCodes.EmptyField,
                customMessage ?: "must not be empty field")

        return ValidatorResult.Success()
    }
}

fun ValidatorsBuild.emptyValidator(customMessage: String? = null) {
    (validatorsList.first { it is EmptyValidator } as EmptyValidator).customMessage = customMessage
}