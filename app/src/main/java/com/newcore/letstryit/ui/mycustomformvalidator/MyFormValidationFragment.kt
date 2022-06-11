package com.newcore.letstryit.ui.mycustomformvalidator

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.core.util.formvalidator2.CheckFieldsMode
import com.newcore.letstryit.core.util.formvalidator2.InputTextClass
import com.newcore.letstryit.core.util.formvalidator2.MyFormField
import com.newcore.letstryit.core.util.formvalidator2.validators.emailValidator
import com.newcore.letstryit.core.util.formvalidator2.validators.emptyValidator
import com.newcore.letstryit.core.util.formvalidator2.validators.numberValidator
import com.newcore.letstryit.core.util.formvalidator2.vmForm
import com.newcore.letstryit.databinding.FragmentFormValidationBinding

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
                emptyValidator("hi")
                emailValidator()
            }

            number = inputField(binding.etNumber, inputTypeClass = InputTextClass.Number) {
                numberValidator { "number only" }
            }

            password = inputField(
                binding.etPassword,
                inputTypeTransformation = InputType.TYPE_TEXT_VARIATION_PASSWORD,
            )

            username = inputField(binding.etUserName)

            submitButton(binding.btnSubmit)
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
            Log.e(TAG, "email: ${email.value}")
            Log.e(TAG, "password: ${password.value}")
            Log.e(TAG, "username: ${username.value}")
            Log.e(TAG, "number: ${it?.getField(binding.etNumber)?.value}")
        }

    }


    private val TAG = "MyFormValidationFragmen"

}

