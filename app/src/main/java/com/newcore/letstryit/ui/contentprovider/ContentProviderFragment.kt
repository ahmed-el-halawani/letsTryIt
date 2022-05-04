package com.newcore.letstryit.ui.contentprovider

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentContentProvideerBinding
import com.newcore.letstryit.model.entites.User
import com.newcore.letstryit.model.repositories.UserRepo

class ContentProviderFragment : BaseFragment<FragmentContentProvideerBinding>
    (FragmentContentProvideerBinding::inflate) {

    val userRepo by lazy {
        UserRepo(requireContext().contentResolver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            saveData()
            clearForm()
        }

        binding.btnShowData.setOnClickListener {
            findNavController().navigate(ContentProviderFragmentDirections.actionContentProviderFragmentToShowContentProviderDataFragment())
        }

    }

    fun clearForm(){
        binding.apply {
            etEmail.setText("")
            etName.setText("")
            etPhoneNumber.setText("")
        }
    }


    fun saveData() {
        userRepo.insert(
            User(
                name = binding.etName.text.toString(),
                email = binding.etEmail.text.toString(),
                phoneNumber = binding.etPhoneNumber.text.toString()
            )
        )
    }

}