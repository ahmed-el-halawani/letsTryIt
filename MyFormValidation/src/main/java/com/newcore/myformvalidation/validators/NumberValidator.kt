package com.newcore.myformvalidation.validators

import com.newcore.myformvalidation.IValidator
import com.newcore.myformvalidation.ValidatorResult
import com.newcore.myformvalidation.ValidatorResultCodes
import com.newcore.myformvalidation.ValidatorsBuild
import java.lang.Long
import kotlin.NumberFormatException
import kotlin.String

class NumberValidator(
    val customMessage: ((String?) -> String)? = null,
) : IValidator {

    override fun validate(value: String?): ValidatorResult {
        try {
            Long.parseLong(value!!.toString())
        } catch (t: NumberFormatException) {
            return ValidatorResult.Failure(
                ValidatorResultCodes.InvalidNumber,
                customMessage?.invoke(value) ?: "please enter valid number"
            )
        }

        return ValidatorResult.Success()
    }
}

fun ValidatorsBuild.numberValidator(
    customMessage: ((String?) -> String)? = null,
) {
    validatorsList.add(NumberValidator(customMessage))
}