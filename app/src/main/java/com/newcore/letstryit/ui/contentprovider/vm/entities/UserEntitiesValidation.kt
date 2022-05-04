package com.newcore.letstryit.ui.contentprovider.vm.entities

import com.newcore.letstryit.util.formvalidator.BaseEntitiesValidator
import com.newcore.letstryit.util.formvalidator.EmailValidator
import com.newcore.letstryit.util.formvalidator.NameValidator
import com.newcore.letstryit.util.formvalidator.PhoneNumberValidator

data class UserEntitiesValidation(
    var phoneNumberValidator: PhoneNumberValidator = PhoneNumberValidator(),
    var emailValidator: EmailValidator = EmailValidator(),
    var nameValidator: NameValidator = NameValidator(),
) : BaseEntitiesValidator() {
    var checkIt = false
        set(value) {
            checkAll()
            field = value
        }

    fun clearForm() {
        phoneNumberValidator = PhoneNumberValidator()
        emailValidator = EmailValidator()
        nameValidator = NameValidator()
        checkIt = false
    }

    private fun checkAll() {
        phoneNumberValidator.isValid()
        emailValidator.isValid()
        nameValidator.isValid()
    }

    override fun isValidForm(): Boolean {
        checkIt = true

        return phoneNumberValidator.isValid()
                && emailValidator.isValid()
                && nameValidator.isValid()
    }
}