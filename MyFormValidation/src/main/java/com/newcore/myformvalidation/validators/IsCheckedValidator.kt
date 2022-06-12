package com.newcore.myformvalidation.validators
import com.newcore.myformvalidation.IValidator
import com.newcore.myformvalidation.ValidatorResult
import com.newcore.myformvalidation.ValidatorResultCodes
import com.newcore.myformvalidation.ValidatorsBuild

class IsCheckedValidator() {
    var customMessage: String? = ""

    fun validate(value: Boolean): ValidatorResult {
        return if (!value)
            ValidatorResult.Failure(
                ValidatorResultCodes.IsChecked,
                customMessage ?: "must not be empty"
            )
        else
            ValidatorResult.Success()
    }
}

