package com.newcore.myformvalidation

import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import androidx.annotation.IdRes
import com.newcore.core.ViewContainer
import com.newcore.myformvalidation.enums.CheckFieldsMode
import com.newcore.myformvalidation.enums.InputTextClass
import com.newcore.myformvalidation.formfield.BaseFormField
import com.newcore.myformvalidation.validators.IsCheckedValidator


class MyForm(private var layoutView: ViewContainer) {

    var checkFieldsMode: CheckFieldsMode = CheckFieldsMode.Always
        private set(value) {
            field = value
            setOnChangeErrorCheck?.invoke(value)
        }

    fun checkFieldsMode(checkFieldsMode: CheckFieldsMode) {
        this.checkFieldsMode = checkFieldsMode
    }

    fun onChangeListener(changeListener: ((String, MyForm) -> Unit)) {
        this.changeListener = changeListener
    }

    fun onSubmitListener(submitListener: ((MyForm?) -> Unit)) {
        this.submitListener = submitListener
        initButton()
    }

    fun onValidForm(validListener: ((MyForm?) -> Unit)) {
        this.validListener = validListener
        initButton()
    }

    fun onInvalidForm(inValidListener: ((BaseFormField<*, *>?) -> Unit)) {
        this.inValidListener = inValidListener
        initButton()
    }

    fun submitButton(@IdRes submitBtn: Int) {
        this.submitBtn = submitBtn
    }

    fun submitButton(submitBtn: View) {
        this.submitBtn = submitBtn.id
    }

    var setOnChangeErrorCheck: ((checkErrorState: CheckFieldsMode) -> Unit)? = null


    fun inputField(
        view: EditText,
        inputTypeClass: InputTextClass? = null,
        inputTypeTransformation: Int? = null,
        isOptional: Boolean = false,
        layoutView: ViewContainer? = null,
        validatorsBuild: (ValidatorsBuild.() -> Unit)? = null,
    ) = inputField(
        view.id, inputTypeClass,
        inputTypeTransformation,
        isOptional,
        layoutView,
        validatorsBuild
    )


    fun inputField(
        @IdRes idRes: Int,
        inputTypeClass: InputTextClass? = null,
        inputTypeTransformation: Int? = null,
        isOptional: Boolean = false,
        layoutView: ViewContainer? = null,
        validatorsBuild: (ValidatorsBuild .() -> Unit)? = null,
    ): MyFormField {
        val vBuilder = ValidatorsBuild()
        validatorsBuild?.invoke(vBuilder)

        val formField =
            MyFormField(idRes,
                vBuilder,
                inputTypeClass,
                inputTypeTransformation,
                isOptional,
                layoutView) {
                changeListener?.invoke(fields.joinToString(separator = ",") {
                    "{key: " + it.id.toString() + " value: " + it.getValue() + " message: " + it.message + " }"
                }, this)
            }

        return formField.also { fields.add(formField) }
    }

    fun checkGroup() {

    }

    fun checkField(
        view: CompoundButton,
        isOptional: Boolean = false,
        layoutView: ViewContainer? = null,
        isCheckedValidator: (IsCheckedValidator.() -> Unit)? = null,
    ): IsCheckedValidator {
        return checkField(view.id, isOptional, layoutView, isCheckedValidator)
    }

    fun checkField(
        @IdRes idRes: Int,
        isOptional: Boolean = false,
        layoutView: ViewContainer? = null,
        isCheckedValidator: (IsCheckedValidator.() -> Unit)? = null,
    ): IsCheckedValidator {
        val checkedValidator = IsCheckedValidator()
        isCheckedValidator?.invoke(checkedValidator)
        fields.add(MyFormCheckField(idRes, isOptional, layoutView, checkedValidator))
        return checkedValidator
    }

    private fun initButton() {
        submitBtn?.let {
            layoutView.findViewById<View>(it).setOnClickListener {
                when (checkFieldsMode) {
                    CheckFieldsMode.AfterFirstSubmit, CheckFieldsMode.Always -> changeShowErrorStateInFields()
                    CheckFieldsMode.EverySubmit -> validateFieldsOnSubmit()
                }

                fieldValidation()

                submitListener?.invoke(this)
            }
        }
    }


    fun start(layoutView: ViewContainer) {
        this.layoutView = layoutView
        fields.forEach { it.updateView(layoutView) }
        fields.forEach { it.setShowErrorState(checkFieldsMode) }
        initButton()
    }


    fun getField(view: View): BaseFormField<*, *> {
        return fields.firstOrNull { it.id == view.id } ?: throw Exception("field not found")
    }

    fun getField(@IdRes id: Int): BaseFormField<*, *> {
        return fields.firstOrNull { it.id == id } ?: throw Exception("field not found")
    }


    private var submitListener: ((MyForm) -> Unit)? = null
    private var validListener: ((MyForm) -> Unit)? = null
    private var inValidListener: ((BaseFormField<*, *>?) -> Unit)? = null
    private var changeListener: ((String, MyForm) -> Unit)? = null

    private var submitBtn: Int? = null
    private val fields = mutableListOf<BaseFormField<*, *>>()

    private fun fieldValidation() {
        val invalidField =
            fields.firstOrNull {
                if (!it.isOptional)
                    !it.isValid()
                else
                    false
            }

        if (invalidField == null)
            validListener?.invoke(this)
        else
            inValidListener?.invoke(invalidField)
    }

    private fun changeShowErrorStateInFields() {
        checkFieldsMode = CheckFieldsMode.Always
        fields.forEach { it.setShowErrorState(CheckFieldsMode.Always) }
    }

    private fun validateFieldsOnSubmit() {
        fields.forEach { it.isValid() }
    }
}