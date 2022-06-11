package com.newcore.letstryit.core.util.formvalidator


class PasswordValidator(value: String? = null) : FieldValidator<String>(value) {
    init {
        checkValidation()
    }

    override fun checkValidation() {
        message = Validators.emptyValidator(value) ?: Validators.lengthValidator(value!!, 20, 8)
    }

    override fun reset(): FieldValidator<String> = PasswordValidator()
}