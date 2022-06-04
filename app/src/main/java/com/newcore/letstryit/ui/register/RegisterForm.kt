package com.newcore.letstryit.ui.register

import com.newcore.letstryit.core.util.formvalidator.*

data class RegisterForm(
    var emailValidator: EmailValidator = EmailValidator(),
    var userNameValidator: NameValidator = NameValidator(),
    var passwordValidator: PasswordValidator = PasswordValidator(),
    var confirmPasswordValidator: ConfirmPasswordValidator = ConfirmPasswordValidator(),
) : FormValidatorWithConfirmPassword() {
    override fun getFieldsValidators() = listOf(
        emailValidator, userNameValidator, passwordValidator, confirmPasswordValidator
    )

    override fun setConfirmPassword(value: String?) {
        confirmPasswordValidator = ConfirmPasswordValidator(value, passwordValidator.value)
    }
}