package com.newcore.letstryit.ui.register

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentRegisterBinding
import com.newcore.letstryit.core.util.extentions.EditTextExtensions.onTextChange
import com.newcore.letstryit.core.util.formvalidator.EmailValidator
import com.newcore.letstryit.core.util.formvalidator.NameValidator
import com.newcore.letstryit.core.util.formvalidator.PasswordValidator

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val vm: RegisterFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        formErrorListener()
        loginStateListener()
        setupFormListener()
        setupFormErrorHandler()

        setupButtons()
    }

    private fun loginStateListener() {
        vm.settingsLiveData().observe(viewLifecycleOwner) {
            if (it.account != null)
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToProfileFragment())
        }
    }

    private fun formErrorListener() {
        vm.registerErrorLiveData.observe(viewLifecycleOwner) {
            binding.tvFormError.isVisible = it != null
            binding.tvFormError.text = it
        }
    }

    private fun setupButtons() = binding.apply {
        btnRegister.setOnClickListener {
            if (vm.saveAndClear())
                clearForm()
        }

        btnGetAllAccounts.setOnClickListener {
            findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToShowAccountsDataFragment()
            )
        }
    }

    private fun setupFormListener() = binding.apply {
        etEmail.onTextChange { text ->
            vm.withForm {
                it.emailValidator = EmailValidator(text.toString())
            }
        }
        etUserName.onTextChange { text ->
            vm.withForm {
                it.userNameValidator = NameValidator(text.toString())
            }
        }
        etPassword.onTextChange { text ->
            vm.withForm {
                it.passwordValidator = PasswordValidator(text.toString())
            }
        }
        etConfirmPassword.onTextChange { text ->
            vm.withForm {
                it.setConfirmPassword(text.toString())
            }
        }
    }

    private fun setupFormErrorHandler() = binding.apply {
        vm.registerFormLiveData.observe(viewLifecycleOwner) {
            etUserName.error = if (!vm.isFormValidatorEnable()) null else
                it.userNameValidator.message
            etPassword.error = if (!vm.isFormValidatorEnable()) null else
                it.passwordValidator.message
            etConfirmPassword.error = if (!vm.isFormValidatorEnable()) null else
                it.confirmPasswordValidator.message
            etEmail.error = if (!vm.isFormValidatorEnable()) null else
                it.emailValidator.message
        }
    }

    private fun clearForm() = binding.apply {
        listOf(etConfirmPassword, etEmail, etPassword, etUserName)
            .forEach { it.setText("") }
    }


}