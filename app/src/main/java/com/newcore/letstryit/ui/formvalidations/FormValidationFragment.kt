package com.newcore.letstryit.ui.formvalidations

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.afollestad.vvalidator.form
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentFormValidationBinding

private const val TAG = "FormValidationFragment"

class FormValidationFragment :
    BaseFragment<FragmentFormValidationBinding>(FragmentFormValidationBinding::inflate) {

    private val vm: FormValidationFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val form = form {
            useRealTimeValidation(disableSubmit = true)
            input(binding.etEmail, "email") {
                isEmail()
                isNotEmpty()
            }
            input(binding.etNumber, "number") {
                isNumber()
                isNotEmpty()
            }
            input(binding.etPassword, "password") {
                isNotEmpty()
            }
            input(binding.etUserName, "username") {
                isNotEmpty()
            }
            submitWith(binding.btnSubmit) {
                Log.e(TAG, "onViewCreated: $it")
                Toast.makeText(
                    requireContext(),
                    it.values().joinToString { field -> field.name + ":" + field.value + "," },
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }


    }

}

