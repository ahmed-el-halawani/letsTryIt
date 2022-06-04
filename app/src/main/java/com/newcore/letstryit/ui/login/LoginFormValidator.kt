package com.newcore.letstryit.ui.login

import com.newcore.letstryit.core.util.formvalidator.EmailValidator
import com.newcore.letstryit.core.util.formvalidator.FieldValidator
import com.newcore.letstryit.core.util.formvalidator.FormValidator
import com.newcore.letstryit.core.util.formvalidator.PasswordValidator

data class LoginFormValidator(
    var emailValidator: EmailValidator = EmailValidator(),
    var passwordValidator: PasswordValidator = PasswordValidator()
): FormValidator() {
    override fun getFieldsValidators(): List<FieldValidator<*>> = listOf(
        emailValidator,passwordValidator
    )
}