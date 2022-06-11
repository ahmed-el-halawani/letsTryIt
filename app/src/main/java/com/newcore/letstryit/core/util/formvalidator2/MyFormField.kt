package com.newcore.letstryit.core.util.formvalidator2

import android.view.View
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.core.widget.doOnTextChanged


data class MyFormField(
    @IdRes val id: Int,
    val layoutView: View,
    private val validatorsBuild: ValidatorsBuild,
    private val onTextChange: ((MyFormField) -> Unit)? = null,
) {

    var value: String = ""
        private set
        get() = this.field.text.toString()

    var message: ValidatorResult = ValidatorResult.Success()
        private set

    fun updateView(layoutView: View) {
        field = layoutView.findViewById(id)
        listener()
    }

    fun isValid(): Boolean = message is ValidatorResult.Success

    fun setShowErrorState(checkFieldsMode: CheckFieldsMode) {
        showError = when (checkFieldsMode) {
            CheckFieldsMode.Always -> true
            CheckFieldsMode.AfterFirstSubmit, CheckFieldsMode.EverySubmit -> false
        }
    }

    fun checkForErrors() {
        message = validatorsBuild.getValidationResult(value)
        if (showError)
            field.error = message.stringMessage
    }


    private var field: EditText

    private var showError: Boolean = true
        set(value) {
            field = value
            checkForErrors()
        }

    private fun listener() {
        field.doOnTextChanged { text, _, _, _ ->
            value = text.toString()
            checkForErrors()
            onTextChange?.invoke(this);
        }
    }

    init {
        field = layoutView.findViewById(id)
        listener()
    }


}