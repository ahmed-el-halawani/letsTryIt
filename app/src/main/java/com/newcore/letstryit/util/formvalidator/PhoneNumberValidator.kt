package com.newcore.letstryit.util.formvalidator

data class PhoneNumberValidator(private val phoneNumber: String? = null) :
    BaseValidator<String>(phoneNumber) {
    init {
        checkValidation()
    }

    override fun checkValidation() {
        message = Validators.run {
            emptyValidator(phoneNumber) ?: numberValidator(phoneNumber!!) ?: lengthValidator(
                phoneNumber!!, 11, 11)
        }
    }
}