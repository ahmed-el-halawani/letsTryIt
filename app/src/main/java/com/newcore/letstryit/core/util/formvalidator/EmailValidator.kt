package com.newcore.letstryit.core.util.formvalidator

class EmailValidator(email: String? = null) : FieldValidator<String>(email) {
    init {
        checkValidation()
    }

    override fun checkValidation() {
        message = Validators.run {
            emptyValidator(value) ?: emailValidator(value!!)
        }
    }

    override fun reset(): FieldValidator<String> = EmailValidator()
}