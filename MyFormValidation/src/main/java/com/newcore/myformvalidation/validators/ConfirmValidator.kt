package com.newcore.myformvalidation.validators

import com.newcore.myformvalidation.IValidator
import com.newcore.myformvalidation.ValidatorResult
import com.newcore.myformvalidation.ValidatorResultCodes
import com.newcore.myformvalidation.ValidatorsBuild

class ConfirmValidator(
    private val confirmFrom: String?,
    private val customMessage: ((String?) -> String)? = null,
) : IValidator {

    override fun validate(value: String?): ValidatorResult {
        if (value != confirmFrom)
            return ValidatorResult.Failure(
                ValidatorResultCodes.InvalidNumber,
                customMessage?.invoke(value) ?: "password and confirm password not the same"
            )
        return ValidatorResult.Success();
    }
}

fun ValidatorsBuild.confirmValidator(
    confirmFrom: String?,
    customMessage: ((String?) -> String)? = null,
) = validatorsList.add(ConfirmValidator(confirmFrom, customMessage))