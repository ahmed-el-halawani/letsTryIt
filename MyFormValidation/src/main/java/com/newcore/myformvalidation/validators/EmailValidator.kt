package com.newcore.myformvalidation.validators
import com.newcore.myformvalidation.IValidator
import com.newcore.myformvalidation.ValidatorResult
import com.newcore.myformvalidation.ValidatorResultCodes
import com.newcore.myformvalidation.ValidatorsBuild

class EmailValidator(
    val customMessage: ((String?) -> String)? = null,
) : IValidator {


    override fun validate(value: String?): ValidatorResult {
        val emailRegex = Regex("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+\$")
        if (!emailRegex.matches(value!!.toString()))
            return ValidatorResult.Failure(
                ValidatorResultCodes.InvalidEmail,
                customMessage?.invoke(value) ?: "You have entered an invalid email address!"
            )

        return ValidatorResult.Success()
    }
}

fun ValidatorsBuild.emailValidator(
    customMessage: ((String?) -> String)? = null,
) = validatorsList.add(EmailValidator(customMessage))