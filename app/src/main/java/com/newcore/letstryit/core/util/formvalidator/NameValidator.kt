package com.newcore.letstryit.core.util.formvalidator

class NameValidator(name: String? = null) : FieldValidator<String>(name) {

    init {
        checkValidation()
    }

    override fun checkValidation() {
        message = Validators.run {
            emptyValidator(value) ?: lengthValidator(value!!, 10, 3)
        }
    }

    override fun reset(): FieldValidator<String> = NameValidator()


}