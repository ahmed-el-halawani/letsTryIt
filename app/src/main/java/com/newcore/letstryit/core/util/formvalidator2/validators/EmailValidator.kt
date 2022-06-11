package com.newcore.letstryit.core.util.formvalidator2.validators

import com.newcore.letstryit.core.util.formvalidator2.IValidator
import com.newcore.letstryit.core.util.formvalidator2.ValidatorResult
import com.newcore.letstryit.core.util.formvalidator2.ValidatorResultCodes
import com.newcore.letstryit.core.util.formvalidator2.ValidatorsBuild

class EmailValidator(
    val customMessage: ((String?) -> String)? = null,
) : IValidator {


    override fun validate(value: String?): ValidatorResult {
        val emailRegex = Regex("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+\$")
        if (!emailRegex.matches(value!!.toString()))
            return ValidatorResult.Failure(ValidatorResultCodes.InvalidEmail,
                customMessage?.invoke(value) ?: "You have entered an invalid email address!")

        return ValidatorResult.Success()
    }
}

fun ValidatorsBuild.emailValidator(
    customMessage: ((String?) -> String)? = null,
) = validatorsList.add(EmailValidator(customMessage))