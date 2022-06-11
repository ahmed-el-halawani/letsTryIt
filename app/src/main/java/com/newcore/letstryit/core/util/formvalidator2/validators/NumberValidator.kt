package com.newcore.letstryit.core.util.formvalidator2.validators

import com.newcore.letstryit.core.util.formvalidator2.IValidator
import com.newcore.letstryit.core.util.formvalidator2.ValidatorResult
import com.newcore.letstryit.core.util.formvalidator2.ValidatorResultCodes
import com.newcore.letstryit.core.util.formvalidator2.ValidatorsBuild
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
            return ValidatorResult.Failure(ValidatorResultCodes.InvalidNumber,
                customMessage?.invoke(value) ?: "please enter valid number")
        }

        return ValidatorResult.Success()
    }
}

fun ValidatorsBuild.numberValidator(
    customMessage: ((String?) -> String)? = null,
) {
    validatorsList.add(NumberValidator(customMessage))
}