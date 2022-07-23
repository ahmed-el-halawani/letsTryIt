package com.newcore.myformvalidation

import android.widget.EditText
import androidx.annotation.IdRes
import androidx.core.widget.doOnTextChanged
import com.newcore.myformvalidation.enums.InputTextClass
import com.newcore.myformvalidation.formfield.BaseFormField


class MyFormField(
    @IdRes id: Int,
    private val validatorsBuild: ValidatorsBuild,
    private val inputTypeClass: InputTextClass? = null,
    private val inputTypeTransformation: Int? = null,
    isOptional: Boolean = false,
    layoutView: ViewContainer? = null,
    private val onTextChange: ((BaseFormField<EditText, String>) -> Unit)? = null,
) : BaseFormField<EditText, String>(id, isOptional, layoutView) {

    override fun getValue(): String =
        field.text.toString()

    override fun checkForErrors(value: String) {
        message = validatorsBuild.getValidationResult(value)
    }

    override fun updateView(layoutView: ViewContainer) {
        super.updateView(layoutView)
        field.apply {
            inputTypeClass?.let {
                inputType = it.classType
            }

            inputTypeTransformation?.let {
                inputType = inputType or (it)
            }
        }
    }

    override fun initListeners() {
        field.doOnTextChanged { text, _, _, _ ->
            checkForErrors(text.toString())
            displayErrorMessage()
            onTextChange?.invoke(this);
        }
    }


}