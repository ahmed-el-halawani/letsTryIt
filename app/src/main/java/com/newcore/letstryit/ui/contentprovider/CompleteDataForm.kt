package com.newcore.letstryit.ui.contentprovider

import com.newcore.letstryit.util.formvalidator.*

data class CompleteDataForm(
    var phoneNumberValidator: PhoneNumberValidator = PhoneNumberValidator(),
    var emailValidator: EmailValidator = EmailValidator(),
    var nameValidator: NameValidator = NameValidator(),
) : FormValidator() {
    override fun getFieldsValidators(): List<FieldValidator<*>> = listOf(
        phoneNumberValidator, emailValidator, nameValidator
    )
}