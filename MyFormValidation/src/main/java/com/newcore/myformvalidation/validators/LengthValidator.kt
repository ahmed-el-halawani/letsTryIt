package com.newcore.myformvalidation.validators

import com.newcore.myformvalidation.IValidator
import com.newcore.myformvalidation.ValidatorResult
import com.newcore.myformvalidation.ValidatorResultCodes
import com.newcore.myformvalidation.ValidatorsBuild

class LengthValidator(
    val lowerBound: Int,
    val upperBound: Int,
    val customMessageLower: ((String?) -> String)? = null,
    val customMessageUpper: ((String?) -> String)? = null,
) : IValidator {
    override fun validate(value: String?): ValidatorResult {
        if (value!!.toString().length > upperBound)
            return ValidatorResult.Failure(
                ValidatorResultCodes.MoreThanUpper,
                customMessageUpper?.invoke(value) ?: "must be lower than $upperBound"
            )
        if (value.toString().length < lowerBound)
            return ValidatorResult.Failure(
                ValidatorResultCodes.LessThanLower,
                customMessageLower?.invoke(value) ?: "must be greater than $lowerBound"
            )
        return ValidatorResult.Success()
    }
}

fun ValidatorsBuild.lengthValidator(
    lowerBound: Int,
    upperBound: Int,
    customMessageLower: ((String?) -> String)? = null,
    customMessageUpper: ((String?) -> String)? = null,
) = validatorsList.add(
    LengthValidator(
        lowerBound,
        upperBound,
        customMessageLower,
        customMessageUpper,
    ),
)
