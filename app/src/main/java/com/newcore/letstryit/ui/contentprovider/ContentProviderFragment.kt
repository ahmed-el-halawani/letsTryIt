package com.newcore.letstryit.ui.contentprovider

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentContentProvideerBinding
import com.newcore.letstryit.core.util.extentions.EditTextExtensions.onTextChange
import com.newcore.letstryit.core.util.formvalidator.EmailValidator
import com.newcore.letstryit.core.util.formvalidator.NameValidator
import com.newcore.letstryit.core.util.formvalidator.PhoneNumberValidator

class ContentProviderFragment : BaseFragment<FragmentContentProvideerBinding>
    (FragmentContentProvideerBinding::inflate) {

    private val vm: ContentProviderFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToForm()
        formErrorHandler()
        setupButtons()
    }

    private fun setupButtons() = binding.apply {
        btnSave.setOnClickListener {
            if (vm.saveAndClear()) clearForm()
        }

        btnShowData.setOnClickListener {
            findNavController().navigate(ContentProviderFragmentDirections.actionContentProviderFragmentToShowContentProviderDataFragment())
        }
    }

    private fun listenToForm() = binding.apply {
        etName.onTextChange { text ->
            vm.withForm {
                it.nameValidator = NameValidator(text.toString())
            }
        }
        etEmail.onTextChange { text ->
            vm.withForm {
                it.emailValidator = EmailValidator(text.toString())
            }
        }
        etPhoneNumber.onTextChange { text ->
            vm.withForm {
                it.phoneNumberValidator = PhoneNumberValidator(text.toString())
            }
        }
    }

    private fun formErrorHandler() = binding.apply {
        vm.userFormLiveData.observe(viewLifecycleOwner) {
            etName.error = if (!vm.isFormValidatorEnable()) null else
                it.nameValidator.message
            etPhoneNumber.error = if (!vm.isFormValidatorEnable()) null else
                it.phoneNumberValidator.message
            etEmail.error = if (!vm.isFormValidatorEnable()) null else
                it.emailValidator.message
        }
    }

    private fun clearForm() = binding.apply {
        listOf(etEmail, etName, etPhoneNumber).forEach { it.setText("") }
    }


}