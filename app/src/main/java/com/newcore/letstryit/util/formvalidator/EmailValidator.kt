package com.newcore.letstryit.util.formvalidator

data class EmailValidator(private val email: String? = null) : BaseValidator<String>(email) {
    init {
        checkValidation()
    }

    override fun checkValidation() {
        message = Validators.run {
            emptyValidator(email) ?: emailValidator(email!!)
        }
    }
}