package com.newcore.letstryit.core.util.formvalidator

abstract class FormValidator {
    var checkIt = false
        private set

    abstract fun getFieldsValidators(): List<FieldValidator<*>>

    fun checkFormValidation(): Boolean {
        if (!checkIt)
            enableValidationChecker()
        return isValidForm()
    }


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