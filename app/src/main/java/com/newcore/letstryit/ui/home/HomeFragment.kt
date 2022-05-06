package com.newcore.letstryit.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnLogin.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                )
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToRegisterFragment()
                )
            }
            btnContentProvider.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToContentProviderFragment()
                )
            }
        }
    }
}