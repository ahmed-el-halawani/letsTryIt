package com.newcore.letstryit.ui.register

import com.newcore.letstryit.util.formvalidator.*

data class RegisterForm(
    var emailValidator: EmailValidator = EmailValidator(),
    var userNameValidator: NameValidator = NameValidator(),
    var passwordValidator: PasswordValidator = PasswordValidator(),
    var confirmPasswordValidator: ConfirmPasswordValidator = ConfirmPasswordValidator(),
) : FormValidator() {
    override fun getFieldsValidators() = listOf(
        emailValidator, userNameValidator, passwordValidator, confirmPasswordValidator
    )

    fun setConfirmPassword(value: String? = null) {
        confirmPasswordValidator = ConfirmPasswordValidator(value, passwordValidator.value)
    }
}