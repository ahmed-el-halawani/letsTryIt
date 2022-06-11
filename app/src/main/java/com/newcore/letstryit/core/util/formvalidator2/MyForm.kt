package com.newcore.letstryit.core.util.formvalidator2

import android.view.View
import android.widget.EditText
import androidx.annotation.IdRes
import com.newcore.letstryit.core.util.formvalidator2.enums.CheckFieldsMode
import com.newcore.letstryit.core.util.formvalidator2.enums.InputTextClass

class MyForm() {
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

    fun onInvalidForm(inValidListener: ((MyFormField?) -> Unit)) {
        this.inValidListener = inValidListener
        initButton()
    }

    fun submitButton(@IdRes submitBtn: Int) {
        this.submitBtn = submitBtn
    }

    fun submitButton(submitBtn: View) {
        this.submitBtn = submitBtn.id
    }


    fun inputField(
        view: EditText,
        inputTypeClass: InputTextClass? = null,
        inputTypeTransformation: Int? = null,
        validatorsBuild: (ValidatorsBuild .() -> Unit)? = null,
    ): MyFormField {
        return inputField(
            view.id, inputTypeClass,
            inputTypeTransformation,
            validatorsBuild
        )
    }


    fun inputField(
        @IdRes idRes: Int,
        inputTypeClass: InputTextClass? = null,
        inputTypeTransformation: Int? = null,
        validatorsBuild: (ValidatorsBuild .() -> Unit)? = null,
    ): MyFormField {
        val vBuilder = ValidatorsBuild()
        validatorsBuild?.invoke(vBuilder)

        val formField = MyFormField(idRes, vBuilder, inputTypeClass, inputTypeTransformation,
            onTextChange = {
                changeListener?.invoke(fields.joinToString(separator = ",") {
                    "{key: " + it.id.toString() + " value: " + it.value + " message: " + it.message + " }"
                }, this)
            })

        return formField.also { fields.add(formField) }
    }

    private fun initButton() {
        submitBtn?.let {
            layoutView.findViewById<View>(it)?.setOnClickListener {
                when (checkFieldsMode) {
                    CheckFieldsMode.AfterFirstSubmit, CheckFieldsMode.Always -> changeShowErrorStateInFields()
                    CheckFieldsMode.EverySubmit -> validateFieldsOnSubmit()
                }

                val invalidField = fields.firstOrNull { !it.isValid() }
                if (invalidField == null)
                    validListener?.invoke(this)
                else
                    inValidListener?.invoke(invalidField)

                submitListener?.invoke(this)
            }
        }
    }

    private lateinit var layoutView: View

    fun start(layoutView: View) {
        this.layoutView = layoutView
        fields.forEach { it.updateView(layoutView) }
        fields.forEach { it.setShowErrorState(checkFieldsMode) }
        initButton()
    }


    fun getField(view: View): MyFormField {
        return fields.firstOrNull { it.id == view.id } ?: throw Exception("field not found")
    }

    fun getField(@IdRes id: Int): MyFormField {
        return fields.firstOrNull { it.id == id } ?: throw Exception("field not found")
    }


    private var submitListener: ((MyForm) -> Unit)? = null
    private var validListener: ((MyForm) -> Unit)? = null
    private var inValidListener: ((MyFormField?) -> Unit)? = null
    private var changeListener: ((String, MyForm) -> Unit)? = null
    private var checkFieldsMode: CheckFieldsMode = CheckFieldsMode.AfterFirstSubmit
    private var submitBtn: Int? = null
    private val fields = mutableListOf<MyFormField>()

    private fun changeShowErrorStateInFields() {
        checkFieldsMode = CheckFieldsMode.Always
        fields.forEach { it.setShowErrorState(CheckFieldsMode.Always) }
    }

    private fun validateFieldsOnSubmit() {
        fields.forEach { it.checkForErrors() }
    }
}