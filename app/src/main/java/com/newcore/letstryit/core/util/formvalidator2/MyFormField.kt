package com.newcore.letstryit.core.util.formvalidator2

import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.core.widget.doOnTextChanged
import com.newcore.letstryit.core.util.formvalidator2.enums.CheckFieldsMode
import com.newcore.letstryit.core.util.formvalidator2.enums.InputTextClass


data class MyFormField(
    @IdRes val id: Int,
    private val validatorsBuild: ValidatorsBuild,
    private val inputTypeClass: InputTextClass? = null,
    private val inputTypeTransformation: Int? = null,
    private val onTextChange: ((MyFormField) -> Unit)? = null,
) {

    private val TAG = "MyFormField"

    var value: String = ""
        private set
        get() = this.field.text.toString()

    var message: ValidatorResult = ValidatorResult.Success()
        private set


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

    fun updateView(layoutView: View) {
        field = layoutView.findViewById<EditText?>(id).apply {
            inputTypeClass?.let {
                inputType = it.classType
            }

            inputTypeTransformation?.let {
                inputType = inputType or (it)
            }

            Log.e(TAG, "inputField: $inputType")
        }
        listener()
    }

    private lateinit var field: EditText

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


}