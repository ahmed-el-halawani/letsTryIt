package com.newcore.myformvalidation

import android.widget.CheckBox
import com.newcore.myformvalidation.formfield.BaseFormField
import com.newcore.myformvalidation.validators.IsCheckedValidator

class MyFormCheckField(
    id: Int, isOptional: Boolean = false,
    private val isCheckedValidator: IsCheckedValidator
) : BaseFormField<CheckBox, Boolean>(id, isOptional) {


    override fun initListeners() {
        field.setOnCheckedChangeListener { compoundButton, b ->
            checkForErrors(b)
            displayErrorMessage()
        }
    }

    override fun getValue() = field.isChecked


    override fun checkForErrors(value: Boolean) {
        message = isCheckedValidator.validate(value)
    }

    override fun isValid(): Boolean {
        return super.isValid()
    }
}