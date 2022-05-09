package com.newcore.letstryit.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentLoginBinding
import com.newcore.letstryit.util.extentions.EditTextExtensions.onTextChange
import com.newcore.letstryit.util.formvalidator.EmailValidator
import com.newcore.letstryit.util.formvalidator.PasswordValidator

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {


    val vm: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFormListener()
        setupFormErrorHandler()
        setupButtons()
        loginErrorListener()
    }

    private fun loginErrorListener() {
        vm.loginErrorLiveData.observe(viewLifecycleOwner) {
            binding.tvFormError.isVisible = it != null
            binding.tvFormError.text = it
        }
    }


    private fun setupButtons() = binding.apply {
        btnLogin.setOnClickListener {
            if (vm.loginAndClear())
                clearForm()
        }

        btnGetSettings.setOnClickListener {
            Log.e("settings", "settingsbutton: " + vm.getSettings() ?: "")
        }
    }

    private fun setupFormListener() = binding.apply {
        etEmail.onTextChange { text ->
            vm.withForm {
                it.emailValidator = EmailValidator(text.toString())
            }
        }

        etPassword.onTextChange { text ->
            vm.withForm {
                it.passwordValidator = PasswordValidator(text.toString())
            }
        }
    }

    private fun setupFormErrorHandler() = binding.apply {
        vm.loginFormLiveData.observe(viewLifecycleOwner) {

            etPassword.error = if (!vm.isFormValidatorEnable()) null else
                it.passwordValidator.message

            etEmail.error = if (!vm.isFormValidatorEnable()) null else
                it.emailValidator.message
        }
    }

    private fun clearForm() = binding.apply {
        listOf(etEmail, etPassword).forEach { it.setText("") }
    }
}