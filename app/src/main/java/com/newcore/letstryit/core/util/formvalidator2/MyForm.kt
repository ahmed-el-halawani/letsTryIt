package com.newcore.letstryit.core.util.formvalidator2

import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

enum class CheckFieldsMode {
    Always, AfterFirstSubmit, EverySubmit
}

enum class InputTextClass(val classType: Int) {
    Number(InputType.TYPE_CLASS_NUMBER),
    Text(InputType.TYPE_CLASS_TEXT),
    DateTime(InputType.TYPE_CLASS_DATETIME),
    Phone(InputType.TYPE_CLASS_PHONE)
}

private const val TAG = "MyForm"

class MyForm(private var layoutView: View) {
    fun checkFieldsMode(checkFieldsMode: CheckFieldsMode) {
        this.checkFieldsMode = checkFieldsMode

    }

    fun onChangeListener(changeListener: ((String, MyForm) -> Unit)) {
        this.changeListener = changeListener
    }

    fun onSubmitListener(submitListener: ((MyForm?) -> Unit)) {
        this.submitListener = submitListener
        start()
    }

    fun onValidForm(validListener: ((MyForm?) -> Unit)) {
        this.validListener = validListener
        start()
    }

    fun onInvalidForm(inValidListener: ((MyFormField?) -> Unit)) {
        this.inValidListener = inValidListener
        start()
    }

    fun submitButton(@IdRes submitBtn: Int) {
        this.submitBtn = submitBtn
    }

    fun submitButton(submitBtn: View) {
        this.submitBtn = submitBtn.id
    }

    fun updateView(layoutView: View) {
        this.layoutView = layoutView
        fields.forEach { it.updateView(layoutView) }
        updateButton()
    }

    fun inputField(
        view: EditText,
        inputTypeClass: InputTextClass? = null,
        inputTypeTransformation: Int? = null,
        validatorsBuild: (ValidatorsBuild .() -> Unit)? = null,
    ): MyFormField {
        //        inputTypeClass?.let {
        //            view.inputType = it.classType
        //        }
        //
        //        inputTypeTransformation?.let {
        //            view.inputType = view.inputType or (it)
        //        }
        //
        //        Log.e(TAG, "inputField: " + view.inputType)

        val vBuilder = ValidatorsBuild()
        validatorsBuild?.invoke(vBuilder)

        val formField = MyFormField(view.id, layoutView, vBuilder, onTextChange = {
            changeListener?.invoke(fields.joinToString(separator = ",") {
                "{key: " + it.id.toString() + " value: " + it.value + " message: " + it.message + " }"
            }, this)
        })

        return formField.also { fields.add(formField) }
    }


    fun inputField(
        @IdRes idRes: Int,
        inputTypeClass: InputTextClass? = null,
        inputTypeTransformation: Int? = null,
        validatorsBuild: (ValidatorsBuild .() -> Unit)? = null,
    ): MyFormField {
        //        inputTypeClass?.let {
        //            view.inputType = it.classType
        //        }
        //
        //        inputTypeTransformation?.let {
        //            view.inputType = view.inputType or (it)
        //        }

        //        Log.e(TAG, "inputField: " + view.inputType)

        val vBuilder = ValidatorsBuild()
        validatorsBuild?.invoke(vBuilder)

        val formField = MyFormField(idRes, layoutView, vBuilder, onTextChange = {
            changeListener?.invoke(fields.joinToString(separator = ",") {
                "{key: " + it.id.toString() + " value: " + it.value + " message: " + it.message + " }"
            }, this)
        })

        return formField.also { fields.add(formField) }
    }

    private fun updateButton() {
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

    fun start() {
        fields.forEach { it.setShowErrorState(checkFieldsMode) }
        updateButton()
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
        fields.forEach { it.setShowErrorState(CheckFieldsMode.Always) }
    }

    private fun validateFieldsOnSubmit() {
        fields.forEach { it.checkForErrors() }
    }

}


fun Fragment.form(myForm: MyForm.() -> Unit): MyForm {
    val form = MyForm(this.requireView())
    myForm(form)
    form.start()
    return form
}