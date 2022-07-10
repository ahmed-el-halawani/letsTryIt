package com.newcore.letstryit.ui.mycustomformvalidator

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentFormValidationBinding
import com.newcore.myformvalidation.MyFormField
import com.newcore.myformvalidation.enums.CheckFieldsMode
import com.newcore.myformvalidation.enums.InputTextClass
import com.newcore.myformvalidation.validators.emailValidator
import com.newcore.myformvalidation.validators.emptyValidator
import com.newcore.myformvalidation.validators.numberValidator
import com.newcore.myformvalidation.vmForm

class MyFormValidationFragment :
    BaseFragment<FragmentFormValidationBinding>(FragmentFormValidationBinding::inflate) {

    private val vm: MyFormValidationFragmentViewModel by viewModels()
    lateinit var email: MyFormField
    lateinit var password: MyFormField
    lateinit var username: MyFormField
    lateinit var number: MyFormField

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val form = vmForm {
            checkFieldsMode(CheckFieldsMode.AfterFirstSubmit)

            email = inputField(binding.etEmail) {
                emptyValidator()
                emailValidator()
            }

            number = inputField(
                binding.etNumber,
                inputTypeClass = InputTextClass.Number,
            ) {
                numberValidator { "number only" }
            }

            password = inputField(
                binding.etPassword,
                inputTypeTransformation = InputType.TYPE_TEXT_VARIATION_PASSWORD,
            )

            username = inputField(binding.etUserName)

            checkField(binding.checkBox) {
                customMessage = "must not be empty"
            }

            submitButton(binding.btnSubmit)
        }


        binding.swchWithEmail.setOnClickListener {
            email.isOptional = binding.swchWithEmail.isChecked
        }

        form.onChangeListener { s, myForm ->
            Log.e(TAG, "onViewCreated: $s")
        }

        form.onSubmitListener {
            Log.e(TAG, "onViewCreated: $it")
        }

        form.onInvalidForm {
            Log.e(TAG, "onInValid: $it")
        }

        form.onValidForm {
            Log.e(TAG, "email: ${email.getValue()}")
            Log.e(TAG, "password: ${password.getValue()}")
            Log.e(TAG, "username: ${username.getValue()}")
            Log.e(TAG, "number: ${it?.getField(binding.etNumber)?.getValue()}")
        }

    }


    private val TAG = "MyFormValidationFragmen"

}

