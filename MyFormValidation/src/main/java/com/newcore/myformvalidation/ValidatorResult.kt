package com.newcore.myformvalidation


sealed class ValidatorResult(
    val messageCode: ValidatorResultCodes? = null,
    val stringMessage: String? = null,
) {
    class Success() : ValidatorResult()
    class Failure(messageCode: ValidatorResultCodes, stringMessage: String? = null) :
        ValidatorResult(messageCode = messageCode, stringMessage = stringMessage)

    override fun toString(): String {
        return "messageCode: $messageCode stringMessage: $stringMessage"
    }
}