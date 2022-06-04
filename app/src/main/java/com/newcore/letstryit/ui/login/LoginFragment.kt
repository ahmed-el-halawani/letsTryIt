package com.newcore.letstryit.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.newcore.letstryit.R
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentLoginBinding
import com.newcore.letstryit.core.util.extentions.EditTextExtensions.onTextChange
import com.newcore.letstryit.core.util.formvalidator.EmailValidator
import com.newcore.letstryit.core.util.formvalidator.PasswordValidator

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val vm: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFormListener()
        loginErrorListener()
        loginStateListener()
        setupFormErrorHandler()

        setupButtons()


    }

    private fun loginStateListener() {
        vm.settingsLiveData().observe(viewLifecycleOwner) {
            if (it.account != null)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToProfileFragment())
                    .also {
                        findNavController().clearBackStack(R.id.loginFragment)
                    }
        }
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