package com.newcore.letstryit.core.util.formvalidator

class PhoneNumberValidator(phoneNumber: String? = null) : FieldValidator<String>(phoneNumber) {

    init {
        checkValidation()
    }

    override fun checkValidation() {
        message = Validators.run {
            emptyValidator(value) ?: numberValidator(value!!) ?: lengthValidator(value!!, 11, 11)
        }
    }

    override fun reset(): FieldValidator<String> = PhoneNumberValidator()

}