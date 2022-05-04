package com.newcore.letstryit.util.formvalidator

data class NameValidator(private val name: String? = null) : BaseValidator<String>(name) {
    init {
        checkValidation()
    }

    override fun checkValidation() {
        message = Validators.run {
            emptyValidator(name) ?: lengthValidator(name!!, 10, 3)
        }
    }
}