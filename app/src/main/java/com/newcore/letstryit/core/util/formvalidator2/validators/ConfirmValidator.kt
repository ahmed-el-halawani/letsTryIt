package com.newcore.letstryit.core.util.formvalidator2.validators

import com.newcore.letstryit.core.util.formvalidator2.IValidator
import com.newcore.letstryit.core.util.formvalidator2.ValidatorResult
import com.newcore.letstryit.core.util.formvalidator2.ValidatorResultCodes
import com.newcore.letstryit.core.util.formvalidator2.ValidatorsBuild

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