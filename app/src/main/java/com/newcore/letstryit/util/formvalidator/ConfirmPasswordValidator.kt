package com.newcore.letstryit.util.formvalidator

class ConfirmPasswordValidator(value: String? = null, private val password: String? = null) :
    FieldValidator<String>(value) {
    init {
        checkValidation()
    }

    override fun checkValidation() {
        message = Validators.emptyValidator(value) ?: Validators.confirmValidator(value, password)
    }

    override fun reset(): FieldValidator<String> = ConfirmPasswordValidator()
}