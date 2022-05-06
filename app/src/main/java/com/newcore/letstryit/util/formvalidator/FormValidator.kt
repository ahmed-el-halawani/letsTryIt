package com.newcore.letstryit.util.formvalidator

abstract class FormValidator {
    var checkIt = false

    fun     checkFormValidation(): Boolean {
        if (!checkIt)
            enableValidationChecker()

        return isValidForm()
    }

    abstract fun getFieldsValidators(): List<FieldValidator<*>>

    private fun enableValidationChecker() {
        checkIt = true
        checkAll()
    }

    private fun checkAll() {
        getFieldsValidators().forEach { it.isValid() }
    }

    private fun isValidForm(): Boolean {
        for (i in getFieldsValidators()) {
            if (!i.isValid())
                return false
        }
        return true
    }
}