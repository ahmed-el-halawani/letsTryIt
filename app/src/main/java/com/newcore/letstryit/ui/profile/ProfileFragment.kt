package com.newcore.letstryit.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    val vm: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginStateListener()

        setupButtons()
        setupView()
    }

    private fun setupView() = binding.apply {
        vm.settingsLiveData().observe(viewLifecycleOwner) {
            tvUserEmail.text = it.account?.email
            tvUserName.text = it.account?.userName
        }
    }

    private fun setupButtons() = binding.apply {
        btnSignOut.setOnClickListener {
            vm.signOut()
        }
    }

    private fun loginStateListener() {
        vm.settingsLiveData().observe(viewLifecycleOwner) {
            if (it.account == null)
                findNavController().navigateUp()
        }
    }

}