package com.newcore.letstryit.ui.contentprovider

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentContentProvideerBinding
import com.newcore.letstryit.ui.contentprovider.vm.ContentProviderFragmentViewModel
import com.newcore.letstryit.util.extentions.EditTextExtensions.onTextChange
import com.newcore.letstryit.util.formvalidator.EmailValidator
import com.newcore.letstryit.util.formvalidator.NameValidator
import com.newcore.letstryit.util.formvalidator.PhoneNumberValidator

class ContentProviderFragment : BaseFragment<FragmentContentProvideerBinding>
    (FragmentContentProvideerBinding::inflate) {


    val vm: ContentProviderFragmentViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToForm()

        vm.userFormLiveData.observe(viewLifecycleOwner) {


            binding.apply {
                if (vm.userForm.checkIt) {
                    etName.error = it.nameValidator.message
                    etPhoneNumber.error = it.phoneNumberValidator.message
                    etEmail.error = it.emailValidator.message
                } else {
                    etName.error = null
                    etPhoneNumber.error = null
                    etEmail.error = null
                }

            }
        }


        binding.btnSave.setOnClickListener {
            if (vm.saveUser()) {
                clearForm()
            }
        }

        binding.btnShowData.setOnClickListener {
            findNavController().navigate(ContentProviderFragmentDirections.actionContentProviderFragmentToShowContentProviderDataFragment())
        }

    }

    private fun listenToForm() {
        binding.apply {
            etName.onTextChange { text ->
                vm.onUserFormChange {
                    it.nameValidator = NameValidator(text.toString())
                }
            }
            etEmail.onTextChange { text ->
                vm.onUserFormChange {
                    it.emailValidator = EmailValidator(text.toString())
                }
            }
            etPhoneNumber.onTextChange { text ->
                vm.onUserFormChange {
                    it.phoneNumberValidator = PhoneNumberValidator(text.toString())
                }
            }
        }
    }

    private fun clearForm() {
        binding.apply {
            etEmail.setText("")
            etName.setText("")
            etPhoneNumber.setText("")
        }
    }


}